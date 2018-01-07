package twistwrap;

import javax.swing.JOptionPane;

public class drawTwistwrap extends dxf12objects {
    // start attributes

    public double[] dataArray = {0,0,0,0,0,0,0,0,0,0,0,0};

    public double length = 0;
    public double width = 0;
    public double depth = 0;
    public String style = "Twistwrap";
    public String unit = "m";
    public String flute = "E";

    public double buffer = 15;
    public double bufferAngCut = 0;
    public double olGap = 2.5;
    public double dInr = 0;
    public double dOtr = 0;
    public double wInr = 0; // ***************
    public double wOtr = 0; 
    public double wAng = 0;
    public double lInr = 0;
    public double lOtr = 0; // Cut in for the Inner section
    
    public double lFlapTop = 0;
    public double lFlapBtm = 0;
    public double wFlapTop = 0;
    public double wFlapBtm = 0;
    public double flapDif = 0;
    
    public double rippaTab = 20;
    public double tabLen = 29;
    public double tabWdt = 15;
    public double tabAng = 5;
    public double tabStep = 4;
    public double tabRadin = 8;    
    public double rippaTabInsert = 4;
    public double PSoverLap = 25;
    
    public double blks1; 
    public double blkn2;
    public double interlockblks1; 
    public double interlockblkn2;

    public double eliteFlexoS1 = 1055;
    public double eliteFlexoN2 = 1575;
    public int noUpS1 = 0;
    public int noUpN2 = 0;

    public String cutlay = "CUT";
    public String crelay = "CREASE";
    public int col = 1; // Line Colour
    public String ltype = "CONTINUOUS"; // Line Type
  // end attributes
  
  // start methods
  // ****************************    
  public String DrawTwistwrap() {    
    
    dxf += dxf_header12();
    
    // draw the main body starting at top Mid left
    this.absMove(0,0);
    Line(-lOtr, 0, crelay);
    relMove(0, -wOtr);
    Line(lOtr, 0, crelay);        
    relMove(0, -dOtr);
    Line(-lOtr, 0, crelay);
    relMove(0, -lFlapBtm);
    Line(lOtr, 0, cutlay);         
    
    this.absMove(0,0);
    relMove(0, dOtr);
    Line(-lOtr, 0, crelay);
    relMove(0, lFlapTop - rippaTab - tabWdt);
    this.PSripTab();
    Line(lOtr - ((tabRadin + tabStep) * 2), 0, "SAFETY"); 
    
    relMove(tabRadin + tabStep, -(rippaTab + tabWdt)); // Center bit of Top P/S
    this.Xaxis = -1;
    this.PSripTab();
    this.Xaxis = 1; // RESET
    
    relMove(tabRadin + tabStep, -(rippaTab));
    Line(tabRadin, rippaTab, "SAFETY"); // *** FIX Later **********
    
    Line(wInr - (tabRadin * 2 + tabStep), 0, "SAFETY");
    relMove(tabRadin + tabStep, -(rippaTab + tabWdt));
    this.Xaxis = -1;
    this.PSripTab();
    this.Xaxis = 1; // RESET
    
    // Right Edge
    relMove(tabRadin + tabStep, -(rippaTab + tabWdt));
    Line(0, -(wFlapTop-(rippaTab + tabWdt) + dInr + lInr + dInr + wFlapBtm), cutlay); // Long Vertical
    Line(-wInr, 0, cutlay);
    Line(0, flapDif, "SAFETY"); //
    Line(0, lFlapBtm + dOtr + olGap, "SAFETY");
    relMove(-lOtr, -(lFlapBtm + dOtr + olGap)); // Move to the Left Edge
    Line(0, (lFlapTop - rippaTab - tabWdt) + dOtr + wOtr + dOtr + lFlapBtm, cutlay); // Long Vertical Left Side
    relMove(lOtr, 0); // Move to centre
    Line(0, -((lFlapTop - rippaTab - tabWdt) + dOtr), "SAFETY");
    
    this.absMove(0,0);
    
    // Width Creases
    Line(0, -(olGap + buffer), cutlay); // L Cut to Top Width Crease / 45 deg start
    Line(buffer, 0, cutlay);
    Line(wInr-buffer, 0, crelay); // Top Width Crease
    relMove(0, dInr);
    Line(-wInr, 0, crelay); // Top Depth
    
    relMove(0, -(dInr + lInr));
    Line(wInr, 0, crelay);
    relMove(0, -dInr);
    Line(-wInr, 0, crelay);        
    
    this.absMove(buffer, -(olGap + buffer));
    
    Line(wAng - (buffer), -(wAng - (buffer)), "CUTCRE6");
    Line(-wAng, -wAng, cutlay); // line back doesnt need to be 45 degrees
    Line(0, 0 - (this.yabs + olGap + buffer), crelay);
    
    //this.dxf += this.dxf_footer12();
    dxf +="  0\r\n";
    dxf +="ENDSEC\r\n";
    dxf +="  0\r\n";
    dxf +="EOF\r\n";
    
    return dxf;     
  } // DrawTwistwrap      
  
  
  protected void PSripTab() {
    
    Line(tabLen, tabAng, "SAFETY");  
    relMove(-tabLen, -tabAng);
    Line(0, tabWdt, "SAFETY");
    Line(tabLen, 0, "SAFETY");
    relMove(-(tabLen - tabStep), 0);
    Line(tabRadin, rippaTab, "SAFETY"); // *** FIX Later *****************************************************************
    
  } // PSripTab
  
  
  
  
  public void allowanceSetup() {
    // General values  
    rippaTab = 20;
    tabLen = 29;
    tabWdt = 15;
    tabAng = 5;
    tabStep = 4;
    tabRadin = 8;
    rippaTabInsert = 4;
    PSoverLap = 25;
    
    double optimumLtopFlap = 0;
    double optimumWtopFlap = 0;
    
    switch (flute) {
      case "E":  // E flute                                                                         
      buffer = 15;
      olGap = 2.5; 
      dInr = depth + 2;
      dOtr = depth + 4.5;
      lInr = length + 2;          
      break;// ==
      
      case "B": // B flute                                                                      
      buffer = 20;
      olGap = 4;
      dInr = depth + 3;
      dOtr = depth + 5;
      lInr = length + 3;          
      break;// ==
      
      case "C": // C flute                                                                      
      buffer = 20;
      olGap = 4;
      dInr = depth + 5;
      dOtr = depth + 8;
      lInr = length + 5;          
      break;// ==        
    } // switch 
    
    lOtr = lInr + (this.buffer * 2);
    wOtr = width + (olGap * 2);
    wInr = width; // - (olGap * 2);
    wAng = (wOtr - (olGap * 2)) / 2;
    
    bufferAngCut = Math.sqrt(buffer*4);
    
    optimumLtopFlap = (wOtr / 2) + PSoverLap;
    optimumWtopFlap = (lInr / 2) + PSoverLap;
    lFlapTop = optimumLtopFlap; // FIX
    wFlapTop = optimumLtopFlap + buffer + olGap + (dOtr - dInr); // last bit is diff of 2 allowances
    lFlapBtm = wOtr / 2; // FIX
    wFlapBtm = lInr + PSoverLap - wFlapTop; // FIX
    double tWside = (buffer + olGap + lInr + dInr + wFlapBtm);
    double tLside = (wOtr + dOtr + lFlapBtm);
    flapDif = tWside - tLside; // Width side - Length Side ** Out by 5???
    
    this.blks1 = lOtr + wInr;
    if(lFlapTop + dOtr + wOtr + dOtr + lFlapBtm > wFlapTop + dInr + lInr + dInr + wFlapBtm) {  // Just in case
      this.blkn2 = lFlapTop + dOtr + wOtr + dOtr + lFlapBtm;
    } else {
      this.blkn2 = wFlapTop + dInr + lInr + dInr + wFlapBtm;            
    } // end of if-else
    
    //      if (depth > length) {
    //        JOptionPane.showMessageDialog(null, "MAKE-UP WARNING /n/r Depth greater than Length - Extra bend may be needed in the turned in depth.", "Warning", JOptionPane.ERROR_MESSAGE);   
    //      }
    // JOptionPane.showMessageDialog(null, Double.toString(lFlapTop) + "*" + Double.toString(dOtr)  + "*" + Double.toString(wOtr) + "*" + Double.toString(dOtr)  + "*" + Double.toString(lFlapBtm) , "Warning", JOptionPane.ERROR_MESSAGE);
  } // allowances
  
  
  
  // end methods
  
  
} // drawTwistwrap Class