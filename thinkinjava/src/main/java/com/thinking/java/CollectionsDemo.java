package com.thinking.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {
   public static void main(String args[]) {
      // create two lists    
      List<String> srclst = new ArrayList<String>(5);
      List<String> destlst = new ArrayList<String>(10);
      
      // populate two lists
      srclst.add("Java");
      srclst.add("is");
      srclst.add("best");
      
      destlst.add("Java");
      destlst.add("is not");
      destlst.add("older");      
      
      // check elements in both collections
      boolean iscommon = Collections.disjoint(srclst, destlst);
      
      System.out.println("No commom elements: "+iscommon);    
   }    
}