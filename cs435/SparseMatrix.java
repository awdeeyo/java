//KEVIN LE
package matrixcomputation;

import java.io.File;
import java.util.List; 
import java.util.ArrayList;
import java.nio.file.*;



public class SparseMatrix {

    //implement all pre-defined methods below and add your own methods as needed.
    
    private Node[] rowHeads, rowHeadsB;
    private Node[] colHeads, colHeadsB;
    private int size, value, i, j;
    

    public SparseMatrix(Node[] r, Node[] c, int m) {
        
        Node temp = new Node(0,0,0);
        rowHeads = r;
        colHeads = c;
        
 if (m == 1){
        rowHeadsB = r;
        colHeadsB = c;
        for(int i=0;i<r.length;i++){
               temp = rowHeadsB[i];
             for(int j=1;j<r.length+1;j++){  //set values for matrixB
                  if(i+1==j){
                     value = i+1;
                     temp.rowLink = new Node(value,i+1,j);
                     temp = temp.rowLink;
                     }
               }    
               temp.rowLink = rowHeadsB[i]; //link final node to head
           }
        
        }
        
   if (m == 2){
        for(int i=0;i<r.length;i++){ //set values for matrixC
               temp = this.rowHeads[i];
             for(int j=1;j<r.length+1;j++){
                  if(i+1==(j+1)%r.length){
                     value = -2*j-(i+1);
                     temp.rowLink = new Node(value,i+1,j);
                     temp = temp.rowLink;
                     }
               }    
               temp.rowLink = this.rowHeads[i]; //link final node to head
           }
        }      
             
   if (m == 3){
        for(int i=0;i<r.length;i++){ //set values for matrixC
               temp = rowHeads[i];
             for(int j=1;j<r.length+1;j++){
                  if((i+1)%2!=0 && j%2!=0 && j!=3){
                     value = (i+1)+j;
                     temp.rowLink = new Node(value,i+1,j);
                     temp = temp.rowLink;
                     }
                  else if(j==3){
                     value = -(i+1);
                     temp.rowLink = new Node(value,i+1,j);
                     temp = temp.rowLink;                
                  }
               }    
               temp.rowLink = rowHeads[i]; //link final node to head
           }
        
        }  
                
          
   if(m==4){
               
      List<Integer> filenum = new ArrayList<>();
      Path currentRelativePath = Paths.get("");
      String s = currentRelativePath.toAbsolutePath().toString();      
 
      try{
          for (String line : Files.readAllLines(Paths.get(s+"\\matrixcomputation\\input.txt"))) {
               for (String part : line.split("\\t|\\r?\\n")) {
                Integer i = Integer.valueOf(part);
                filenum.add(i);
           }
         }
      }
      catch(Exception e){
        
      }
      
      int size=filenum.get(0);
      int numNodes=(filenum.size()-1)/3;
      Node[] fileNodes = new Node[numNodes];  
    
      int j=0;
      for(int i=0;i<numNodes;i++){
         fileNodes[i]=new Node(filenum.get(j+3),filenum.get(j+1),filenum.get(j+2));
         j+=3;
      }
      for(int i=0;i<size;i++){
         temp=rowHeads[i];
         for (j=0;j<numNodes;j++){
            if(fileNodes[j].row==rowHeads[i].row){
               temp.rowLink=fileNodes[j];
               temp=temp.rowLink;
            }
         }
         temp.rowLink=rowHeads[i];
      }
        
   }  
      
        
        
      Node tempc = new Node(0,0,0); //link all columns into circular linkedlist
      Node tempr = new Node(0,0,0); 
      for(int i=0;i<r.length;i++){
            tempc = colHeads[i];
            for(j=0;j<r.length;j++){
                 tempr = rowHeads[j];
                 if(rowHeads[j].rowLink.col==colHeads[i].col){
                        tempc.colLink=rowHeads[j].rowLink;
                        tempc = tempc.colLink;
                        rowHeads[j]=rowHeads[j].rowLink;
                     
                 }              
            
             }  
   
            tempc.colLink = this.colHeads[i];  
      
         }
         
         for(int i=0;i<r.length;i++){ //re-allign linkedlist so rowHeads[i] is the head of the list
              
               while(rowHeads[i].col!=0){
                  rowHeads[i]=rowHeads[i].rowLink;
               }        
               
         }
            
   }
         

    public static SparseMatrix initializeByInput(File file) {
    

      List<Integer> filenum = new ArrayList<>();
      Path currentRelativePath = Paths.get("");
      String s = currentRelativePath.toAbsolutePath().toString();      
 
      try{
          for (String line : Files.readAllLines(Paths.get(s+"\\matrixcomputation\\"+file))) {
               for (String part : line.split("\\t|\\r?\\n")) {
                Integer i = Integer.valueOf(part);
                filenum.add(i);
           }
         }
      }
      catch(Exception e){
        
      }
      
      int size=filenum.get(0);
      Node[] r = new Node[size];
      Node[] c = new Node[size];
         
      for(int i=0;i<size;i++){
         r[i]=new Node(0,i+1,0);
         c[i]=new Node(0,0,i+1);
    }
         SparseMatrix mata = new SparseMatrix(r,c,4);
         SparseMatrix result = mata;
         return result;

    }

    //parameter n --> given matrix size n
    public static SparseMatrix[] initializeByFormula(int n) {
    Node[] rheadB = new Node[n];
    Node[] cheadB = new Node[n];
    
    Node[] rheadC = new Node[n];
    Node[] cheadC = new Node[n];
    
    Node[] rheadD = new Node[n];
    Node[] cheadD = new Node[n];
    
    SparseMatrix[] matarray = new SparseMatrix[3];
 
      for(int i=0;i<n;i++){
         rheadB[i]=new Node(0,i+1,0);
         cheadB[i]=new Node(0,0,i+1);
    }
          
      for(int i=0;i<n;i++){
         rheadC[i]=new Node(0,i+1,0);
         cheadC[i]=new Node(0,0,i+1);
    }  
    
      for(int i=0;i<n;i++){
         rheadD[i]=new Node(0,i+1,0);
         cheadD[i]=new Node(0,0,i+1);
    }  
         
         
                           
                SparseMatrix matb = new SparseMatrix(rheadB,cheadB,1);
                SparseMatrix matc = new SparseMatrix(rheadC,cheadC,2);
                SparseMatrix matd = new SparseMatrix(rheadD,cheadD,3);

                

          
;
 
        SparseMatrix[] result = {matb,matc,matd};
        return result;
    }

    public void print() {
       int n = getSize();
       for(int i=0;i<n;i++){
            for (j=0;j<n;j++){
                  if(rowHeads[i].rowLink.col==colHeads[j].col){
                  
                     
                        
                        if(rowHeads[i].rowLink.value <= -10){
                              
                              if(rowHeads[i].rowLink.value <= -100){
                              
                                    System.out.print(" " + (int)rowHeads[i].rowLink.value);
                                    rowHeads[i] = rowHeads[i].rowLink;
                                    }
                                    
                               else{
                                    System.out.print(" " + (int)rowHeads[i].rowLink.value + " ");
                                    rowHeads[i] = rowHeads[i].rowLink;
                              }
                        
                        }
                        
                        else{
                        
                           if(rowHeads[i].rowLink.value < 0){
                        
                              System.out.print(" " + (int)rowHeads[i].rowLink.value + "  ");
                              rowHeads[i] = rowHeads[i].rowLink;
                        
                        }
                           else{
                              if(rowHeads[i].rowLink.value >=10){
                                  if(rowHeads[i].rowLink.value >=100){
                                       
                                    System.out.print(" " + (int)rowHeads[i].rowLink.value + " ");
                                    rowHeads[i] = rowHeads[i].rowLink;
                                    }
                                       
                                  else{
                                  System.out.print(" " + (int)rowHeads[i].rowLink.value + "  ");
                                  rowHeads[i] = rowHeads[i].rowLink;
                                     }
                                 }
        
                            else{
                              System.out.print("  " + (int)rowHeads[i].rowLink.value + "  ");
                              this.rowHeads[i] = rowHeads[i].rowLink;
                                 }
                              
                        }
                   }
               }
                  
                  else {
                     if(rowHeads[i].value <= -1000 || rowHeads[i].value >= 1000){
                     
                              if(rowHeads[i].value <= -10000 || rowHeads[i].value >= 10000){  
                                  if(rowHeads[i].value <= -10000){                        
                                   System.out.print(" 0 ");  
                                   }  
                                  else{
                                   System.out.print(" 0 ");
                                  }                         
                              }                             
                              else if(rowHeads[i].value <= -1000){
                                   System.out.print("  0 ");
                              }  
                              else{
                                   System.out.print("  0 ");
                              }                       
                           }                    
                     else{
                     System.out.print("  0  ");
                     }
                  }
              }
              System.out.println(" ");
              
         }
    }

    //parameter m --> another sparse matrix m
    public SparseMatrix add(SparseMatrix m) {
        int size=getSize();
        double value;
        Node[] addRowHead = new Node[size];
        Node[] addColHead = new Node[size];
        Node temp = new Node(0,0,0);

        
       for(int i=0;i<size;i++){
         addRowHead[i]=new Node(0,i+1,0);
         addColHead[i]=new Node(0,0,i+1);
      }
      for(int i=0;i<size;i++){
        this.rowHeads[i] = this.rowHeads[i].rowLink;
        m.rowHeads[i] = m.rowHeads[i].rowLink;
      }      

        for(int i=0;i<size;i++){
               temp = addRowHead[i];
             for(int j=0;j<size;j++){  //set values for added matrix
                  if(this.rowHeads[i].rowLink.col==m.rowHeads[i].rowLink.col
                   && this.rowHeads[i].rowLink.col==j+1 && m.rowHeads[i].rowLink.col==j+1){
                   
                     value = this.rowHeads[i].rowLink.value + m.rowHeads[i].rowLink.value;
                     temp.rowLink = new Node(value,i+1,j+1);
                     temp = temp.rowLink;
                     this.rowHeads[i] = this.rowHeads[i].rowLink;
                     m.rowHeads[i] = m.rowHeads[i].rowLink;
                     }
                     else if (this.rowHeads[i].rowLink.col!=j+1 
                     && m.rowHeads[i].rowLink.col!=j+1){

                        //no node created for '0' value
                     
                     }
                     else if (this.rowHeads[i].rowLink.col==0 || m.rowHeads[i].rowLink.col==0){
                        
                        if (m.rowHeads[i].rowLink.col==0){
                        value = this.rowHeads[i].rowLink.value;
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        this.rowHeads[i] = this.rowHeads[i].rowLink;  
                        }
                        
                        else{
                        value = m.rowHeads[i].rowLink.value;
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        m.rowHeads[i] = m.rowHeads[i].rowLink;
                        }                   
                     
                     }
                     else if(this.rowHeads[i].rowLink.col < m.rowHeads[i].rowLink.col){
                        value = this.rowHeads[i].rowLink.value;
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        this.rowHeads[i] = this.rowHeads[i].rowLink; 
                     }
                     else{
                        value = m.rowHeads[i].rowLink.value;
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        m.rowHeads[i] = m.rowHeads[i].rowLink;

                     }
               }    
               temp.rowLink = addRowHead[i]; //link final node to head
           }
        
       
 
        SparseMatrix added = new SparseMatrix(addRowHead,addColHead,0);
        
        SparseMatrix result = added;
        return result;
    }

    //parameter m --> another sparse matrix m
    public SparseMatrix subtract(SparseMatrix m) {
    
        int size=getSize();
        double value;
        Node[] subRowHead = new Node[size];
        Node[] subColHead = new Node[size];
        Node temp = new Node(0,0,0);

        
       for(int i=0;i<size;i++){
         subRowHead[i]=new Node(0,i+1,0);
         subColHead[i]=new Node(0,0,i+1);
      }
      for(int i=0;i<size;i++){
        this.rowHeads[i] = this.rowHeads[i].rowLink;
        m.rowHeads[i] = m.rowHeads[i].rowLink;
      }      

        for(int i=0;i<size;i++){
               temp = subRowHead[i];
             for(int j=0;j<size;j++){  //set values for added matrix
                  if(this.rowHeads[i].rowLink.col==m.rowHeads[i].rowLink.col
                   && this.rowHeads[i].rowLink.col==j+1 && m.rowHeads[i].rowLink.col==j+1){
                   
                     value = this.rowHeads[i].rowLink.value - m.rowHeads[i].rowLink.value;

                     temp.rowLink = new Node(value,i+1,j+1);
                     temp = temp.rowLink;
                     this.rowHeads[i] = this.rowHeads[i].rowLink;
                     m.rowHeads[i] = m.rowHeads[i].rowLink;
                     }
                     else if (this.rowHeads[i].rowLink.col!=j+1 
                     && m.rowHeads[i].rowLink.col!=j+1){
                         
                         //no node created for '0' value
                     
                     }
                     else if (this.rowHeads[i].rowLink.col==0 || m.rowHeads[i].rowLink.col==0){
                        
                        if (m.rowHeads[i].rowLink.col==0){
                        value = this.rowHeads[i].rowLink.value;
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        this.rowHeads[i] = this.rowHeads[i].rowLink;  
                        }
                        
                        else{
                        value = -1*(m.rowHeads[i].rowLink.value);
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        m.rowHeads[i] = m.rowHeads[i].rowLink;
                        }                   
                     
                     }
                     else if(this.rowHeads[i].rowLink.col < m.rowHeads[i].rowLink.col){
                        value = this.rowHeads[i].rowLink.value;
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        this.rowHeads[i] = this.rowHeads[i].rowLink; 
                     }
                     else{
                        value = -1*(m.rowHeads[i].rowLink.value);
                        temp.rowLink = new Node(value,i+1,j+1);
                        temp = temp.rowLink;
                        m.rowHeads[i] = m.rowHeads[i].rowLink;

                     }
               }    
               temp.rowLink = subRowHead[i]; //link final node to head
           }
        
       

        SparseMatrix subtract = new SparseMatrix(subRowHead,subColHead,0);
    
        SparseMatrix result = subtract;
        return result;
    }

    //integer a
    public SparseMatrix scalarMultiply(int a) { 
        
        int size=getSize();
        double value;
        Node[] scRowHead = new Node[size];
        Node[] scColHead = new Node[size];
        Node temp = new Node(0,0,0);

        
       for(int i=0;i<size;i++){
         scRowHead[i]=new Node(0,i+1,0);
         scColHead[i]=new Node(0,0,i+1);
      }
      for(int i=0;i<size;i++){
        this.rowHeads[i] = this.rowHeads[i].rowLink;
      }      
      
      for(int i=0;i<size;i++){
      
               temp = scRowHead[i];
               
             for(int j=0;j<size;j++){ 
             
                  if(this.rowHeads[i].rowLink.col==j+1){
                  value = ((double)a)*(this.rowHeads[i].rowLink.value);
                  temp.rowLink = new Node(value,i+1,j+1);
                  temp = temp.rowLink;
                  this.rowHeads[i] = this.rowHeads[i].rowLink;
                  
                  }
                     
         }
         
         temp.rowLink = scRowHead[i]; //link final node to head
         
    }

        SparseMatrix scalar = new SparseMatrix(scRowHead,scColHead,0);

        SparseMatrix result = scalar;
        return result;
    }

    //parameter m --> another sparse matrix m
    public SparseMatrix matrixMultiply(SparseMatrix m) {
    
        int size=getSize();
        double value,add;
        Node[] multRowHead = new Node[size];
        Node[] multColHead = new Node[size];
        Node temp = new Node(0,0,0);

        
       for(int i=0;i<size;i++){
         multRowHead[i]=new Node(0,i+1,0);
         multColHead[i]=new Node(0,0,i+1);
      }
      for(int i=0;i<size;i++){
        this.rowHeads[i] = this.rowHeads[i].rowLink;
      }      
      
      for(int i=0;i<size;i++){
      
               temp = multRowHead[i];
               
             for(int j=0;j<size;j++){ 
             
                     value = 0;
                     add = 0;
             
                     for(int k=0;k<size;k++){
                        
                        if(this.rowHeads[i].rowLink.col==k+1 || m.colHeads[j].colLink.row==k+1){
                           
                           if(this.rowHeads[i].rowLink.col==k+1 && m.colHeads[j].colLink.row==k+1){
                                               
                                 add = this.rowHeads[i].rowLink.value * m.colHeads[j].colLink.value;
                                 value = value + add;
                                 this.rowHeads[i] = this.rowHeads[i].rowLink;
                                 m.colHeads[j] = m.colHeads[j].colLink;                     
                              
                              }
                              
                              else if(this.rowHeads[i].rowLink.col==k+1){
                           
                              this.rowHeads[i] = this.rowHeads[i].rowLink;
                           
                                 }
                                 
                                 
                              else{
                                 m.colHeads[j] = m.colHeads[j].colLink;
                              }
                                                       
                           }
                                             
                        }
                         if(value != 0){ //no nodes created for '0' value
                            temp.rowLink = new Node(value,i+1,j+1);
                            temp = temp.rowLink;
                            m.colHeads[j] = m.colHeads[j].colLink;
                            this.rowHeads[i] = this.rowHeads[i].rowLink;
                            }
                        else{
                            m.colHeads[j] = m.colHeads[j].colLink;
                            this.rowHeads[i] = this.rowHeads[i].rowLink;
                        }
                    }
                   
                  temp.rowLink = multRowHead[i]; //link final node to head
                                         
         }
        SparseMatrix multiply = new SparseMatrix(multRowHead,multColHead,0);
         
        SparseMatrix result = multiply;
        return result;
    }

    //integer c
    public SparseMatrix power(int c) {
        SparseMatrix powered = this,temp = this, nextTemp = this;
        int counter = c, two = 2;
        
        while(counter!=0){
            if(counter == c){
                   temp = this.matrixMultiply(this);
                   nextTemp = this.matrixMultiply(this); 
                   c = c - two; 
                   two = two * 2;
                }  
            else if(c > two){
                   nextTemp = temp.matrixMultiply(temp);
                   c = c - two;
                   two = two * 2;
                }  
            if(c < two){
                 if(counter >= two){
                        powered = nextTemp.matrixMultiply(nextTemp);
                        counter = counter - two;
                 }
               
                   else if(counter == 2){
                        powered = powered.matrixMultiply(temp);
                        counter = counter - 2;
                    }
                   
                   else if(counter == 1){
                        powered = powered.matrixMultiply(this);
                        counter = counter - 1;
                    }
                }  
           }
        SparseMatrix result = powered;
        return result;
    }
    
    //transpose matrix itself
    public SparseMatrix transpose() {
    
        int size=getSize();
        double value;
        Node[] transRowHead = new Node[size];
        Node[] transColHead = new Node[size];
        Node tempR = new Node(0,0,0);
        Node tempC = new Node(0,0,0);

        
       for(int i=0;i<size;i++){
         transRowHead[i]=new Node(0,i+1,0);
         transColHead[i]=new Node(0,0,i+1);
         }
         
        for(int j=0;j<size;j++){
        this.colHeads[j] = this.colHeads[j].colLink;
      }  
         
       for(int k=0;k<size;k++){
      
               tempR = transRowHead[k];
               tempC = this.colHeads[k];

               for(int m=0;m<size;m++){
               
                   if(tempC.row != 0){
                      value = tempC.value;
                      tempR.rowLink = new Node(value,tempC.col,tempC.row);
                      tempR = tempR.rowLink;
                      tempC = tempC.colLink;
                      
                    }
              
               }
               
                tempR.rowLink = transRowHead[k]; //link final node to head
                    
           }
                 
            
        SparseMatrix transpose = new SparseMatrix(transRowHead,transColHead,0);
        SparseMatrix result = transpose;
        return result;     
     
  }

    public int getSize(){
      List<Integer> matSize = new ArrayList<>();
      Path currentRelativePath = Paths.get("");
      String s = currentRelativePath.toAbsolutePath().toString();      
 
      try{
          for (String line : Files.readAllLines(Paths.get(s+"\\matrixcomputation\\input.txt"))) {
               for (String part : line.split("\n")) {
                Integer i = Integer.valueOf(part);
                matSize.add(i);
           }
         }
      }
      catch(Exception e){
        
      }
      int size = matSize.get(0);

      return size;
    }
    
    
}

