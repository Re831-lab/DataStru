/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.myarraylist;

/**
 *
 * @author Dell
 */
 /* Write a Java method that receives two arryLists then prints the elements of the first 
arrayList that are not found in the second one. 
 Note: The method should be written in the MyArrayList class  (your class)  
Example: arr1 = {1,2, 2, 5,4,8,9,7,3} arr2 = {2,8,6,7} Output:  The elements are: 1, 5, 4, 9, 3
*/
import java.util.ArrayList;

public class MyArrayList {
    
    
    public void printDifference(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        System.out.print("The elements are: ");
        boolean isFirst = true;
        
        for (Integer element : list1) {
            if (!list2.contains(element)) {
                if (!isFirst) {
                    System.out.print(", ");
                }
                System.out.print(element);
                isFirst = false;
                
               
                list2.add(element);
            }
        }
        
        System.out.println();
    }
    
   
    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(1); arr1.add(2); arr1.add(2); arr1.add(5); arr1.add(4);
        arr1.add(8); arr1.add(9); arr1.add(7); arr1.add(3);
        
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(2); arr2.add(8); arr2.add(6); arr2.add(7);
        
        MyArrayList myList = new MyArrayList();
        myList.printDifference(arr1, arr2);
    }
}
