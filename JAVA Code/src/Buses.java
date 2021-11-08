//package application of dijkstras Algorithm

import java.util.*;

class Stack{
	int top=-1;
	int stackArray[]=new int[8];
	
	void push(int x)
	{
		stackArray[++top]=x;
	}
	
	int pop()
	{
		if(top==-1)
			return 0;
		return stackArray[top--];
	}	
  }

class ArrDepData{
	
	String Busname[]=new String[8];
	int BusNumber[]=new int[8];
	int BusCost[]=new int[8];
	
	ArrDepData(String A[],int flno[],int C[])
	{
		Busname=A;
		BusNumber=flno;
		BusCost=C;
	}
  }

class VertexNames{
 
  String VertexNames[]=new String[8];
  VertexNames()
  {
	  //LOCATIONS
	  VertexNames[0]="CHN";//CHENNAI
	  VertexNames[1]="CUD";//CUDDALORE
	  VertexNames[2]="TNJ";//THANJAVUR
	  VertexNames[3]="RNP";//RAMANATHAPURAM
	  VertexNames[4]="KAN";//KANYAKUMARI
	  VertexNames[5]="MDU";//MADURAI
	  VertexNames[6]="VEL";//VELLORE
	  VertexNames[7]="CBE";//COIMBATORE
  }
  
  int getBusDepoasIndex(String DepBuspt)
  {
	 int i=0;
	 try {
		  	while(VertexNames[i].equalsIgnoreCase(DepBuspt)==false)
		  	{
			  i++;
		  	}
		  	return i;
	 	 }catch (Exception e)
	 {
	    System.out.println("Location not in the specific array or list of locations we have selected");
	    System.exit(0);
	 }
	 return i;
  }
 
  String getBusDepoName(String DepBust)
  {
	  switch(DepBust)
	  {
	  	case "CHN":
	  		return "CHENNAI";
	  	case "CUD":
	  		return "CUDDALORE";
	  	case "TNJ":
	  		return "THANJAVUR";
	  	case "RNP":
	  		return "RAMANATHAPURAM";
	  	case "KAN":
	  		return "KANYAKUMARI";
	  	case "MDU":
	  		return "MADURAI";
	  	case "VEL":
	  		return "VELLORE";
	  	case "CBE":
	  		return "COIMBATORE";
	  	default: return "Not Found";
	  }
  }
}

public class Buses {
	
	public static int tot_nodes=8;
	public static int tot_edges=12;
	public static int path[]=new int[8];
	static Scanner s=new Scanner(System.in);
	static VertexNames BUST=new VertexNames();
	static ArrDepData Schedule[]=new ArrDepData[8];
	static Stack Buffer=new Stack();
	static long MinimumTime;
	
	public static void main(String[] args){
		
		int i,j;
		long cost[][]=new long[8][8];
		long dist[]=new long[8];
 
		String DepartureBusTerminal;
		String ArrivalBusTerminal;
		System.out.print("\t\t\t\t\t\t\t-----BUS ROUTING System using Dijkstra's Algorithm----- \n\n");
		System.out.println("\t\t\t\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		System.out.println("\t\t\t\t\t\t\t -----TAMIL NADU Bus Transportation Corporation-----\n");
		System.out.println("\t\t\t\t\t\t\t ---------------------------------------------------\n");
		System.out.println("\t\t\t\t\t\t\t ------------------Destination codes----------------\n");
		System.out.println("\t\t\t\t\t ---------------------------------------------------------------------------------");
		System.out.print("\n\t\t\t\t\t\t\t\t\t    CHN-> CHENNAI\n"
		         	   	 + "\t\t\t\t\t\t\t\t\t    CUD-> CUDDALORE\r\n"
		         	   	 + "\t\t\t\t\t\t\t\t\t    TNJ-> THANJAVUR\r\n"
		         	   	 + "\t\t\t\t\t\t\t\t\t    RNP-> RAMANATHAPURAM\r\n"
		         	   	 + "\t\t\t\t\t\t\t\t\t    KAN-> KANYAKUMARI\r\n"
		         	   	 + "\t\t\t\t\t\t\t\t\t    MDU-> MADURAI\r\n"
		         	   	 + "\t\t\t\t\t\t\t\t\t    VEL-> VELLORE\r\n"
		         	   	 + "\t\t\t\t\t\t\t\t\t    CBE-> COIMBATORE\n\n");
		System.out.println("\t\t\t\t\t ---------------------------------------------------------------------------------");
		System.out.println("\t\t\t ----------[-o--o] [-o--o] [-o--o] [-o--o] [-o--o] [-o--o] [-o--o] [-o--o] [-o--o] [-o--o] [-o--o] [-o--o]---------- \n");
		create(cost);
		System.out.println("-------------------------------------------------");
		
		System.out.print("Enter the Departure Bus Terminal code: ");
		DepartureBusTerminal=s.next();
		i=BUST.getBusDepoasIndex(DepartureBusTerminal);
		
		System.out.print("Enter the Destination Bus Terminal code: ");
		ArrivalBusTerminal=s.next();
		int A=BUST.getBusDepoasIndex(ArrivalBusTerminal);
 
		System.out.println("-------------------------------------------------");
 
		System.out.println("\nBuses departing from "+(BUST.VertexNames[i])+" BusTerminal to "+(BUST.VertexNames[A])+" are: \n");
		j=A;
		
		Dijkstra(cost,i,dist);
		if(dist[i]==9999)
			System.out.println("\nNo Path from "+BUST.VertexNames[i]+" to "+BUST.VertexNames[j]);
		else
			display(i,j,dist);
	}

	public static void create(long cost[][])
	{
		int i,j;
		String Busname[];
		int BusNumber[];
		int BusCost[];
 
		for(i=0;i<tot_nodes;i++)
		{
			for(j=0;j<tot_nodes;j++)
			{
				if(i==j)
					cost[i][j]=0;
				else
					cost[i][j]=9999;
			}
		}
 
		cost[0][1]=cost[1][0]=150;
		cost[0][5]=cost[5][0]=420;
		cost[0][6]=cost[6][0]=110;
		cost[1][2]=cost[2][1]=130;
		cost[1][5]=cost[5][1]=270;
		cost[2][3]=cost[3][2]=160;
		cost[2][4]=cost[4][2]=350;
		cost[3][4]=cost[4][3]=220;
		cost[3][6]=cost[6][3]=390;
		cost[3][7]=cost[7][3]=280;
		cost[4][5]=cost[5][4]=210;
		cost[6][7]=cost[7][6]=310;
 
		Busname=new String[] {"VolvoLines    ","bRed Busways  ","VolvoLines    "};
		BusNumber=new int[] {784,486,777,-1}; 
		BusCost=new int[] {450,650,500,-1};
		Schedule[6]=new ArrDepData(Busname,BusNumber,BusCost);
 
		Busname=new String[] {"bRed Busways  ","bRed Busways  ","bRed Busways  ","VolvoLines    "};
		BusNumber=new int[] {433,223,213,197,-1};
		BusCost=new int[] {800,650,700,500,-1};
 		Schedule[7]=new ArrDepData(Busname,BusNumber,BusCost);

 		Busname=new String[] {"WeRL Buslines ", "bRed Busways  ","VolvoLines    ", "bRed Busways  "};
		BusNumber=new int[] {566,311,259,448,-1};
		BusCost=new int[] {900,350,500,600,-1};
 		Schedule[4]=new ArrDepData(Busname,BusNumber,BusCost);

 		Busname=new String[] {"VolvoLines    ","VolvoLines    ","bRed Busways  ","bRed Busways  ","VolvoLines    "};
		BusNumber=new int[] {648,448,742,445,287,-1};
		BusCost=new int[] {550,750,600,800,450,-1};
		Schedule[0]=new ArrDepData(Busname,BusNumber,BusCost);
		
		Busname=new String[] {"WeRL Buslines ","VolvoLines    ","bRed Busways  ","bRed Busways  ","VolvoLines    "};
		BusNumber=new int[] {124,667,446,824,334,-1};
		BusCost=new int[] {450,650,500,1000,700,-1};
		Schedule[1]=new ArrDepData(Busname,BusNumber,BusCost);

		Busname=new String[] {"WeRL Buslines ", "VolvoLines    ","WeRL Buslines ","WeRL Buslines ","bRed Busways  "};
		BusNumber=new int[] {156,187,934,438,555,-1};
		BusCost=new int[] {450,650,500,1200,600,-1};
 		Schedule[5]=new ArrDepData(Busname,BusNumber,BusCost);

 		Busname=new String[] {"VolvoLines    ","bRed Busways  ", "WeRL Buslines ", "VolvoLines    ","bRed Busways  ","VolvoLines    "};
		BusNumber=new int[] {789,963,846,748,225,499,-1};
		BusCost=new int[] {450,650,500,700,400,900,-1};
 		Schedule[2]=new ArrDepData(Busname,BusNumber,BusCost);

 		Busname=new String[] {"bRed Busways  ","bRed Busways  ","WeRL Buslines ", "VolvoLines    ","VolvoLines    ","VolvoLines    "};
		BusNumber=new int[] {986,45,965,102,202,333,-1};
		BusCost=new int[] {450,650,500,1300,1000,500,-1};
		Schedule[3]=new ArrDepData(Busname,BusNumber,BusCost);
	}

	public static void Dijkstra(long[][] cost, int source, long[] dist)
	{
		int i,j,v1,v2;
		long minD;
		int src[]=new int[10];
		
		for(i=0;i<tot_nodes;i++)
		{
			dist[i]=cost[source][i];
			src[i]=0;
			path[i]=source;
		}
		src[source]=1;
		
		for(i=1;i<tot_nodes;i++)
		{
			minD=9999;
			v1=-1;
			for(j=0;j<tot_nodes;j++)
			{
				if(src[j]==0)
				{	
					if(dist[j]<minD)
					{
						minD=dist[j];
						v1=j;
					}
				}
			}
			src[v1]=1;
			
			for(v2=0;v2<tot_nodes;v2++)
			{
				if(src[v2]==0)
				{
					if((dist[v1]+cost[v1][v2])<dist[v2])
					{
						dist[v2]=dist[v1]+cost[v1][v2];
						path[v2]=v1;
					}
				}
			}
		}
	}

	public static void display(int Source,int Destination,long dist[])
	{
		int i;
		System.out.println("The route from "+BUST.VertexNames[Source]+" to "+BUST.VertexNames[Destination]+" is: \n");
		
		for(i=Destination;i!=Source;i=path[i])
		{
			System.out.print(BUST.VertexNames[i]+" <--> ");
			Buffer.push(i);
		}
		System.out.println(""+BUST.VertexNames[i]);
		Buffer.push(i);

		System.out.println("\nThe Bus Details on your route are: ");
		System.out.println("______________________________________________________________________________");
		
		showData(Destination);
		
		System.out.println("______________________________________________________________________________");
	}

	public static void showData(int dest)
	{
		int i=Buffer.pop();
		while(i!=dest)
		{
			// System.out.println(i);
			System.out.println("\n From BUS TERMINAL ----> "+BUST.VertexNames[i]+"\n\n     BUS TERMINAL\t TRAVEL COST   DESTINATION CODE   DESTINATION NAME");
			System.out.println(" _ ________________________________________________________________________ _");
			System.out.println();
			
			for(int j=0;Schedule[i].BusNumber[j]!=-1;j++)
			{
				int k=Buffer.pop();
				Buffer.push(k);
				System.out.println("    "+Schedule[i].Busname[j]+""+Schedule[i].BusNumber[j]+"\t   Rs "+Schedule[i].BusCost[j]+"/-\t    -"+BUST.VertexNames[k] + "-\t     " + BUST.getBusDepoName(BUST.VertexNames[k]));
			}
			
			i=Buffer.pop();
			System.out.println(" _ ________________________________________________________________________ _");
		}
		
		System.out.println();
		Buffer.pop();
	}

}