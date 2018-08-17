/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import java.util.LinkedList;

/**
 *
 * @author akillhalimi
 */
public class SliderInformation {
    
    private boolean added = false;
    public double origianl;
  public SliderInformation() {
      
  }
  
  public boolean getAdded(){
      return added;
  }
  
  public void setAdded(boolean added) {
      this.added=added;
  }
  public void add(Double db) {
      if(!added)
          origianl = db;
  }
  
  @Override
  public String toString() {
     return null;
  }
}
