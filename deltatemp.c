/*abhivandit*/
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

struct inputsignal
{
  double x;
  double y;
}input[100];
struct outputsignal{
	double x;
	double y;
}output[100];
int structnumber;
int outputcounter=0;
double slope[100];
double delta;
double sampletime;
void getData(){
  FILE *fptr;
    char filename[15];
    printf("Enter the filename to be opened \n");
    scanf("%s", filename);
    /*  open the file for reading */
    fptr = fopen(filename, "r");
    if (fptr == NULL)
    {
        printf("Cannot open file \n");
        exit(0);
       
    }
    else{
    	int counter=0;
    	structnumber=0;
    	double lx;
    	
    	 /*while (EOF != fscanf(fptr, "%lf,%lf", &input[structnumber].x, &input[structnumber].y))
                           
        {
        	printf("holla %lf",input[structnumber].x);
            structnumber++;
        }*/

           while ( fscanf(fptr, "%lf", & lx) == 1 )  
             { 
              if(counter==0){
              	//printf("%lf",lx);
              	input[structnumber].x=lx;//floorf(lx * 100) / 100;;

              	//printf("%lf",input[structnumber].x);
              	counter++;
              }
              else{
              	input[structnumber].y=lx;
              	counter--;
              	structnumber++;
              }
             } 

    	
     }
    fclose(fptr);


}
void printData(){
	int i=0;
	for(i=0;i<structnumber;i++){
		printf("%.2f %.2f",input[i].x,input[i].y);
	}
}
int struct_cmp_by_xcoordinate(const void *a, const void *b) 
{ 
    struct inputsignal *ia = (struct inputsignal *)a;
    struct inputsignal *ib = (struct inputsignal *)b;
    return (int)(100.f*ia->x - 100.f*ib->x);
	/* float comparison: returns negative if b > a 
	and positive if a > b. We multiplied result by 100.0
	to preserve decimal fraction */ 
 
} 
double maxSlope(){
	double tempslope;
	int i=0;
	double max=0;
	slope[0]=(-1.0);
	for(i=1;i<structnumber;i++){
		tempslope=fabs((input[i].y-input[i-1].y)/(input[i].x-input[i-1].x));
		slope[i]=tempslope;
		//printf("%lf ",tempslope);
		if(tempslope>max){
			max=tempslope;
		}
	}
	return max;
}
void generatesampletime(double tempsampletime){
	sampletime=0;
	while(sampletime>tempsampletime || sampletime==0){
		if(tempsampletime<=1.0){
		sampletime=(double)rand()/RAND_MAX;}
		else{
			sampletime=(double)rand()/RAND_MAX+rand()%((int)tempsampletime);
		}
	 }
}
void deltamodulation(){
	double totdelta=0;
	int slopecounter=1;
	double currentconstant=0;//y=mx+c
	double currentime=0;
	double currenty=0;//currentsignalcoordinates;
	int flag=0;
	int structurecounter=1;
	double quantizederror=0;
	double previousdelta=0.0;
	double previoustime=0.0;
	int extremeflag=0;

	FILE *fptr,*fptr2;
	fptr = fopen("staircase.txt", "w");
	fptr2=fopen("input.txt","w");
    if (fptr == NULL)
    {
        printf("Cannot open file \n");
        exit(0);
       
    }
    else{
    	
	


	while(flag==0){


	currentconstant=input[structurecounter].y-(slope[slopecounter])*input[structurecounter].x;
	while(currentime<input[structurecounter].x){

	
	currenty=slope[slopecounter]*currentime+currentconstant;//current value of input 
	if((currentime+sampletime)>=input[structnumber-1].x){
	fprintf(fptr2,"%lf %lf",currentime,currenty);}
	else{
				fprintf(fptr2,"%lf %lf\n",currentime,currenty);
	}
	if(totdelta<currenty){
	if(extremeflag!=0){
		output[outputcounter].x=previoustime;
			output[outputcounter].y=previousdelta;
			outputcounter++;
		extremeflag=0;
	}
		fprintf(fptr,"%lf %lf\n",currentime,previousdelta);
		quantizederror=quantizederror+fabs(currenty-totdelta);
		printf("1 ");

		totdelta=totdelta+delta;
//fprintf(fptr,"%lf %lf\n",currentime,totdelta);
			if(currentime+sampletime>=input[structnumber-1].x){
		fprintf(fptr,"%lf %lf",currentime,totdelta);}
		else{

fprintf(fptr,"%lf %lf\n",currentime,totdelta);
		}
		previousdelta=totdelta;
		previoustime=currentime;

	}
	else if(totdelta>currenty){
		if(extremeflag==0){
			output[outputcounter].x=previoustime;
			output[outputcounter].y=previousdelta;
			outputcounter++;
			extremeflag=1;

		}
		fprintf(fptr,"%lf %lf\n",currentime,previousdelta);
		totdelta=totdelta-delta;
		quantizederror=quantizederror+fabs(currenty-totdelta);
		if((currentime+sampletime)>=input[structnumber-1].x){
		fprintf(fptr,"%lf %lf",currentime,totdelta);}
		else{

fprintf(fptr,"%lf %lf\n",currentime,totdelta);
		}
		previousdelta=totdelta;
		previoustime=currentime;
		printf("0 ");
		
		
	}//elseif
	/*else{
				fprintf(fptr,"%lf %lf\n ",currentime,previousdelta);
		fprintf(fptr,"%lf %lf\n ",currentime,totdelta);
		previoustime=currentime;
	}*/

	/*if((currentime+sampletime)>input[structnumber-1].x){
			fprintf(fptr,"%lf %lf\n ",currentime,previousdelta);
		fprintf(fptr,"%lf %lf",currentime,totdelta);
		previousdelta=totdelta;
		previoustime=currentime;
	}
	else{
		fprintf(fptr,"%lf %lf\n ",currentime,previousdelta);
		fprintf(fptr,"%lf %lf\n",currentime,totdelta);
		previousdelta=totdelta;
		previoustime=currentime;
	}*/
	currentime=currentime+sampletime;

}//while

if(currentime>=input[structnumber-1].x){
	printf("currenttime%lf",currentime);
	flag=1;
}//if
if(currentime>=input[structurecounter].x){
	slopecounter++;
	structurecounter++;
}//if
}//while flag
}
fclose(fptr);
fclose(fptr2);
printf("error %lf ",quantizederror);
}
void createplottingfiles(){
	FILE *fptr;
	fptr = fopen("output.txt", "w");
    if (fptr == NULL)
    {
        printf("Cannot open file \n");
        exit(0);
       
    }
    else{
    	int i=0;
		for(i=0;i<outputcounter;i++){
			fprintf(fptr,"%lf %lf\n",output[i].x,output[i].y);
			if(i==(outputcounter-1)){
				fprintf(fptr,"%lf %lf",output[i].x,output[i].y);
			}


	}//for
}//else
fclose(fptr);


}
int main(){
	getData();
	double maxslopevalue;
	double tempsampletime;
	double currentsampletime=0;
	//printf("%lf",input[0].x);
	//printData();
	qsort(input, structnumber, sizeof(struct inputsignal ), struct_cmp_by_xcoordinate);    
	//printData();
	printf("enter the delta value");
	scanf("%lf",&delta);
	maxslopevalue=maxSlope();
	printf("%lfmax",maxslopevalue);
	tempsampletime=delta/maxslopevalue;
	printf("%lf\n",tempsampletime );
	//generatesampletime(tempsampletime);
	sampletime=tempsampletime;
	printf("%lf",sampletime);
	deltamodulation();
	createplottingfiles();
	system("python plot.py");
	return 0;



}
