import java.util.*;

import java.util.Arrays;

public class HackerankSemiMergesort {
       
          static void quickSort(int[] ar) 
       {
                    List<Integer> out=partition(ar);
                    System.out.print("Final list: ");
                    for(int i=0;i<out.size();i++)
                    {
                          System.out.print(out.get(i)+" ");
                    }
                    System.out.println();
       }   
        static List<Integer> partition(int[] ar) 
       {
          int pivot=ar[0];
          List<Integer> list1 = new ArrayList<Integer>();
          List<Integer> list2 = new ArrayList<Integer>();
          if(ar.length==1)
          {    
          	
              list1.add(pivot);
              list1.addAll(list2);

              return list1;
          }
          for(int i=1;i<ar.length;i++)
          {
            if(ar[i]<pivot)
            {
                list1.add(ar[i]);    
            }
            else if(ar[i]>pivot)
                list2.add(ar[i]);
          }
          

            int[] a=new int[list1.size()];
            int[] b=new int[list2.size()];
            
            for (int i = 0; i < a.length; i++) {
                a[i] = list1.get(i);
            }
            for (int j = 0; j < b.length; j++) {
                b[j] = list2.get(j);
            }
            List<Integer> last = new ArrayList<Integer>();
            if(a.length!=0)
            	last.addAll(partition(a));
            
            	last.add(pivot);
            if(b.length!=0)
            	last.addAll(partition(b));
            for(int i=0;i<last.size();i++)
            {
                  System.out.print(last.get(i)+" ");
            }
            System.out.println();
            return last;
       } 
        
        
 
      static void printArray(int[] ar) {
         for(int n: ar){
            System.out.print(n+" ");
         }
           System.out.println("");
      }
       
      public static void main(String[] args) {
           int[] ar={5, 8, 1, 3, 7, 9, 2};
           quickSort(ar);
         }    
   }
