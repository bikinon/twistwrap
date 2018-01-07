package twistwrap;


/**
  *
  * Description
  *
  * @version 1.0 from 7/30/2016
  * @author Tim Gathercole
  */

public class dxf12objects {
  
  // start attributes
  public double xabs = 0;
  public double yabs = 0;
  public int hndl = 1000;
  // 1,1 = Right hand, pointing up Flap
  public int Xaxis = 1; // Direction of X axis +1 or -1
  public int Yaxis = 1; // Direction of Y axis +1 or -1
   
   
  public String dxf = "";
  public String dxfxmax = "10";
  public String dxfymax = "10";
  public String dimasz = "5.5";
  public String dimtxt = "7.1";
  public String dimexe = "2.2";
  public String lunits = "2";
  public String luprec = "4";
  public String dimdec = "9";
  public String dimzin = "8";
  public String dimexo = "0.1";
  public String dimgap = "0.1";

  private String cr = "\r\n";
  // end attributes
  
  // start methods
  protected String dxf_header12() {
    // DXF Version 12 Header - Simplified version
    //    String cr = "\r\n";
    
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="HEADER"+ cr;
    dxf +="  9"+ cr;
    dxf +="$ACADVER"+ cr;
    dxf +="  1"+ cr;
    dxf +="AC1009"+ cr; // DXF Version 12
    dxf +="  9"+ cr;
    dxf +="$EXTMIN"+ cr; // X, Y, and Z drawing extents 10, 20, 30 lower-left corner (in WCS)
    dxf +=" 10"+ cr;
    dxf +="0"+ cr;
    dxf +=" 20"+ cr;
    dxf +="0"+ cr;
    dxf +="  9"+ cr;
    dxf +="$EXTMAX"+ cr; // X, Y, and Z drawing extents 10, 20, 30 upper-right corner (in WCS)
    dxf +=" 10"+ cr;
    dxf += dxfxmax + cr;
    dxf +=" 20"+ cr;
    dxf += dxfymax + cr;
    dxf +="  0"+ cr;
    dxf +="ENDSEC"+ cr;
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="TABLES"+ cr;
    dxf +="  0"+ cr;
    dxf +="TABLE"+ cr;
    dxf +="  2"+ cr;
    dxf +="LAYER"+ cr;
    dxf +=" 70"+ cr;
    dxf +=" 1"+ cr;
    dxf +="  0"+ cr;
    dxf +="LAYER"+ cr;
    dxf +="  2"+ cr;
    dxf +="Design"+ cr;
    dxf +=" 70"+ cr;
    dxf +="64"+ cr;
    dxf +=" 62"+ cr;
    dxf +="7"+ cr;
    dxf +="  6"+ cr;
    dxf +="CONTINUOUS"+ cr;
    dxf +="  0"+ cr;
    dxf +="ENDTAB"+ cr;
    dxf +="  0"+ cr;
    dxf +="ENDSEC"+ cr;
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="BLOCKS"+ cr;
    dxf +="  0"+ cr;
    dxf +="ENDSEC"+ cr;
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="ENTITIES"+ cr;
    
    return dxf;
  }
  
  
  protected String dxf_footer12() {
    /* WONT PRINT */
    //  Final part of the DXF File
    //  DXF version 12
    //  String cr = "\r\n"; // Chr(10).Chr(13);
    
    dxf +="  0" + cr;
    dxf +="ENDSEC" + cr;
    dxf +="  0" + cr;
    dxf +="EOF" + cr;
    
    return "";
  }
  
  protected boolean Line(double xval, double yval, String layer) {
    // DXF version 12
    // xval    = Incremental X movement
    // yval    = Incremental Y movement
    // layer   = Layer to use
    // eColor    = Colour to use
    // eLinetype = Line type to use
    //echo xval.'*'.yval.'*'.layer.'*'.eColor.'*'.eLinetype.'<br>';
    // String cr = "\r\n";
    
    dxf +="  0" + cr;
    dxf +="LINE" + cr;
    dxf +="  5" + cr; //  Drawing Datadbase Handle
    dxf +=hndl + cr;
    dxf +="  8" + cr; //  Layer name
    dxf += "Design" + cr; // layer + cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += getLineCol(layer) + cr;
    
    dxf +=" 10" + cr; //  Start X
    dxf += xabs + cr;
    dxf +=" 20" + cr; //  Start Y
    dxf += yabs + cr;
    dxf +=" 30" + cr; //  Start Z
    dxf +="0.0" + cr;
    dxf +=" 11" + cr; //  End X
    dxf += String.valueOf(xabs + xval * Xaxis) + cr;
    dxf +=" 21" + cr; //  End Y
    dxf += String.valueOf(yabs + yval * Yaxis) + cr;
    dxf +=" 31" + cr; //  End Z
    dxf +="0.0" + cr;
    //  Finish
    xabs = xabs + xval * Xaxis;
    yabs = yabs + yval * Yaxis;
    hndl = hndl + 1;
    return false;
  }
  
  
  protected String getLineCol(String layer) {
    // Return a colour for a layer from its Layer Name 
    LayerLineType oBjt = new LayerLineType();
    oBjt.layer = layer;
    return oBjt.getLineColour();
  } // getLineCol
  
  
  protected boolean circle(double radius, String layer, int eColor) {
    /*
    * Make a relative move to the center of the circle and call this function
    */
    //  String cr = "\r\n";
    
    dxf +="  0"+ cr;
    dxf +="CIRCLE"+ cr;
    
    dxf +="  5"+ cr; //  Drawing Datadbase Handle
    dxf +=hndl+ cr;
    dxf +="  8"+ cr; //  Layer name
    dxf +="Design" + cr; //layer+ cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += getLineCol(layer) + cr;
    
    dxf +=" 10"+ cr; //  center X
    dxf +=xabs+ cr;
    dxf +=" 20"+ cr; //  center Y
    dxf +=yabs+ cr;
    dxf +=" 30"+ cr; //  center Z
    dxf +="0.0"+ cr;
    dxf +=" 40"+ cr; //  Radius
    dxf +=radius+ cr;
    
    //  Finish
    hndl = hndl + 1;
    
    return true;
  }
  
  /**
  * 
  * @param XcenterAbs
  * @param YcenterAbs
  * @param radius
  * @param startAngle (0-90-180-270) Mid not Top Str
  * @param endAngle
  * @param endPosX - Incremental movement to update AbsX
  * @param endPosY - to update AbsY
  * @param layer
  * @return DXF Arc
  */
  protected boolean arcDxf(double XcenterAbs, double YcenterAbs, double radius, double startAngle, double endAngle, double endPosX, double endPosY, String layer) {
    /* A simple implementation of the DXF Entity
    * 
    */
    // String cr = "\r\n";
    
    dxf +="  0" + cr;
    dxf +="ARC" + cr;
    
    dxf +="  5" + cr; //  Drawing Datadbase Handle
    dxf += Integer.toString(hndl) + cr;
    dxf +="  8" + cr; //  Layer name
    dxf +="Design" + cr; //layer + cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += getLineCol(layer) + cr;
    
    dxf +=" 10" + cr; //  center X
    dxf += XcenterAbs + cr;
    dxf +=" 20" + cr; //  center Y
    dxf += YcenterAbs + cr;
    
    dxf +=" 30" + cr; //  center Z
    dxf +="0.0" + cr;
    
    dxf +=" 40" + cr; //  Radius
    dxf +=radius + cr;
    
    dxf +=" 50" + cr; //  Start Angle of Arc
    dxf +=startAngle + cr;
    dxf +=" 51" + cr; //  End Angle of Arc
    dxf +=endAngle + cr;
    
    //  Finish
    xabs = xabs + endPosX;
    yabs = yabs + endPosY;
    hndl += 1;
    
    return false;
  }
  
  
  
  protected boolean drwArcNOTWORKING(double strX, double strY, double endX, double endY, double radius, int direction, String layer) {
    /* Version of the old Arden ARC command
    *   
    */
    //  double data = this.ArcCenter(P1x, P1y, P2x, P2y, Radius, Direction, ErrorDescription);  
    //    
    //    
    //    String cr = "\r\n";
    //    
    //    dxf +="  0" + cr;
    //    dxf +="ARC" + cr;
    //    
    //    dxf +="  5" + cr; //  Drawing Datadbase Handle
    //    dxf += Integer.toString(hndl) + cr;
    //    dxf +="  8" + cr; //  Layer name
    //    dxf +="Design" + cr; //layer + cr;
    //    
    //    dxf +=" 6" + cr; // Line Type
    //    dxf +="CONTINUOUS" + cr;
    //    dxf +=" 62" + cr; // Line Colour
    //    dxf += getLineCol(layer) + cr;
    //    
    //    dxf +=" 10" + cr; //  center X
    //    dxf += XcenterAbs + cr;
    //    dxf +=" 20" + cr; //  center Y
    //    dxf += YcenterAbs + cr;
    //    
    //    dxf +=" 30" + cr; //  center Z
    //    dxf +="0.0" + cr;
    //    
    //    dxf +=" 40" + cr; //  Radius
    //    dxf +=radius + cr;
    //    
    //    dxf +=" 50" + cr; //  Start Angle of Arc
    //    dxf +=startAngle + cr;
    //    dxf +=" 51" + cr; //  End Angle of Arc
    //    dxf +=endAngle + cr;
    //    
    //    //  Finish
    //    xabs = xabs + endPosX;
    //    yabs = yabs + endPosY;
    //    hndl += 1;
    
    return false;
  } 
  
  
  protected boolean arcPROB_NOT_WORKING(double xEndPt, double yEndPt, double radius, String layer, int cw) {
    // DXF version 12
    // xval   = Incremental X movement to End point
    // yval   = Incremental Y movement to End point
    // radius   = Radius of Arc
    // layer    = Layer to use    (CUT)
    // eColor   = Colour to use  (1)
    // cw     = Clockwise movement of arc: -1 = CW, 1 = CCW so we can just multiply values  CW = std
    
    // Find Centre point of arc and
    double arcArray[] = {0, 0, 0, 0}; // {0 => 0, 1 => 0, 'startAngle' => 0, 'endAngle' => 0}
    arcArray = getArcAngles(xEndPt, yEndPt, radius, cw);
    arcArray[0] = xabs + arcArray[0];
    arcArray[1] = yabs + arcArray[1];
    
    //   String cr = "\r\n";
    
    dxf +="  0"+ cr;
    dxf +="ARC"+ cr;
    
    dxf +="  5"+ cr; //  Drawing Datadbase Handle
    dxf +=hndl+ cr;
    dxf +="  8"+ cr; //  Layer name
    dxf +="Design" + cr; //layer+ cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += getLineCol(layer) + cr;
    
    dxf +=" 10"+ cr; //  center X
    dxf +=arcArray[0]+ cr;
    dxf +=" 20"+ cr; //  center Y
    dxf +=arcArray[1]+ cr;
    
    dxf +=" 30"+ cr; //  center Z
    dxf +="0.0"+ cr;
    
    dxf +=" 40"+ cr; //  Radius
    dxf +=radius+ cr;
    
    dxf +=" 50"+ cr; //  Start Angle of Arc
    dxf +=arcArray[2]+ cr;
    dxf +=" 51"+ cr; //  End Angle of Arc
    dxf +=arcArray[3]+ cr;
    
    //  Finish
    xabs = xabs + xEndPt * Xaxis;
    yabs = yabs + yEndPt * Yaxis;
    hndl = hndl + 1;
    
    return true;
    
  } // arc - DXF v12
  
  protected double[] getArcAngles(double IncMoveX, double IncMoveY, double radius, int cw) {
    /* Partial conversion from HPGL2DXF - Largely a Special case protected boolean for 0422 slots
    * Find
    * Xcenter, Ycenter
    * startAngle, endAngle
    */
    // array('Xcenter' => 0, 'Ycenter' => 0, 'startAngle' => 0, 'endAngle' => 0) ** Java doesn't support associative arrays **
    double rtnArray[] = {0, 0, 0, 0};
    
    double rx =  (IncMoveX / 2) * Xaxis ; // absX - Xc
    double ry =  (IncMoveY / 2) * Yaxis; // absY - Yc
    double ang = -1; // set-up
    double aplus = 0; // set-up
    double angle = 0; // not required?
    double wrx = 0;
    double wry = 0;
    double tmp = 0;
    double strang = 0;
    double endang = 0;
    
    if (rx == 0) {
      angle = 180; // simple 180 arc
      //  rad = Abs(ry);
      if (ry > 0) {
        ang = 270; //90;
      } else {
        ang = 90; //270;
      }
      //GoTo arnxt
    } else if (ry == 0) {
      angle = 180; // simple 180 arc
      // rad = Abs(rx)
      if (rx > 0) {
        ang = 180; //0;
      } else {
        ang = 0; //180;
      }
      // GoTo arnxt
      //  rad = (Abs(rx) * Abs(rx)) + (Abs(ry) * Abs(ry)) // *** Radius is Known in this instance ***
      //  rad = Sqr(rad)
      
    } else if (rx > 0 && ry > 0) {
      aplus = 0;
      wrx = rx;
      wry = ry;
    } else if (rx < 0 && ry > 0) {
      aplus = 90;
      wrx = ry;
      wry = rx;
    } else if (rx < 0 && ry < 0) {
      aplus = 180;
      wrx = rx;
      wry = ry;
    } else if (rx > 0 && ry < 0) {
      aplus = 270;
      wrx = ry;
      wry = rx;
    }
    
    if (ang == -1) { // not touched by the first 2 conditions
      ang = Math.abs(wry) / Math.abs(wrx);  /* angle if not 0/90/180/270 */
      ang = Math.atan(ang);
      ang = (ang / 3.141592654) * 180;  /* rad2deg */
    }
    //arnxt:
    //  radius = rad
    
    strang = ang + aplus;
    endang = strang + angle;
    if (endang > 360) {
      endang = endang - 360;
    }
    
    /* start_angle = (strang * 3.141592654) /180  deg2rad */
    /* end_angle = (endang * 3.141592654) /180  deg2rad */
    if (Yaxis == -1) { // flipped slot
      tmp = strang;
      strang = endang;
      endang = tmp;
    }
    
    rtnArray[0] = rx; // Xcenter
    rtnArray[1] = ry; // Ycenter
    rtnArray[2] = strang; // startAngle
    rtnArray[3] = endang; // endAngle
    
    return rtnArray;
  } // getArcAngles
  
  protected void relMove(double relX, double relY) {
    // Relative movement - takes global absolute co-ordinatates and adds a relative movement to them
    xabs = xabs + relX * Xaxis;
    yabs = yabs + relY * Yaxis;  
  }
  
  protected void absMove(double relX, double relY) {
    // Absolute movement - takes global absolute co-ordinatates and adds a movement to them
    xabs = relX; //* Xaxis;
    yabs = relY; //* Yaxis;  
  }
  
  
  /**
  * 
  * @param P1x Start X
  * @param P1y Start Y
  * @param P2x End X
  * @param P2y End Y
  * @param Radius
  * @param Direction 2=CW, 3=CC
  * @param ErrorDescription String
  * @return ArcCntrX ArcCntrY StartAngle EndAngle
  */
  protected double[] ArcCenter(double P1x, double P1y, double P2x, double P2y, double Radius, int Direction, String ErrorDescription)
  {
    /* returns arc center based on start and end points, radius and arc direction (2=CW, 3=CCW)
    ** Radius can be negative (for arcs over 180 degrees)
    ** Added StartAngle & EndAngle to returned values - THATS NOT WORKING!
    */
    double Angle = 0, AdditionalAngle = 0, L1 = 0, L2 = 0, Diff=0;
    double AllowedError = 0.002;
    double CenterX = 0;
    double CenterY = 0;
    ErrorDescription = "";
    double T1x;
    double T1y;
    double T2x;
    double T2y;
    
    double StartAngle = 0;
    double EndAngle = 0;
    double[] rtnAry = {0,0,0,0}; // = new double[4];
    
    // Sort points depending of direction
    if (Direction == 2)
    {
      T1x = P2x;
      T1y = P2y;
      T2x = P1x;
      T2y = P1y;
    }
    else // 03
    {
      T1x = P1x;
      T1y = P1y;
      T2x = P2x;
      T2y = P2y;
    }
    
    // find angle arc covers
    Angle = CalculateAngle(T1x, T1y, T2x, T2y);
    
    L1 = PointDistance(T1x, T1y, T2x, T2y) / 2;
    Diff = L1 - Math.abs(Radius);
    
    if (Math.abs(Radius) < L1 && Diff > AllowedError)
    {
      ErrorDescription = "Error - wrong radius";
      //  return  String.valueOf(CenterX) + "," + String.valueOf(CenterY);
      rtnAry[0] = CenterX;
      rtnAry[1] = CenterY;
      rtnAry[2] = StartAngle;
      rtnAry[3] = EndAngle;
      return rtnAry;
    }
    
    L2 = Math.sqrt(Math.abs(Math.pow(Radius,2) - Math.pow(L1,2)));
    
    if (L1 == 0)
    AdditionalAngle = Math.PI / 2;
    else
    AdditionalAngle = Math.atan(L2 / L1);
    
    // Add or subtract from angle (depending of radius sign)
    if (Radius < 0)
    Angle -= AdditionalAngle;
    else
    Angle += AdditionalAngle;
    
    // calculate center (from T1)
    CenterX = (double) (T1x + Math.abs(Radius) * Math.cos(Angle)); 
    CenterY = (double) (T1y + Math.abs(Radius) * Math.sin(Angle));
    
    //double StartAngle = Math.atan2(P1y-CenterY, P1x - CenterX);
    StartAngle = Math.toDegrees(Math.atan2(P2y - CenterY, P2x - CenterX));
    EndAngle = Math.toDegrees(Math.atan2(P1y - CenterY, P1x - CenterX)); // ArcTan2(ey-cy, ex-cx)
    
    //return String.format("%.9f",CenterX) + "," + String.format("%.9f",CenterY) + "**" + String.valueOf(StartAngle + "**" + String.valueOf(EndAngle));
    rtnAry[0] = CenterX;
    rtnAry[1] = CenterY;
    rtnAry[2] = StartAngle;
    rtnAry[3] = EndAngle;
    return rtnAry;
  }
  
  
  protected double CalculateAngle(double P1x, double P1y, double P2x, double P2y)
  {
    /* Part of ArcCenter */
    // returns Angle of line between 2 points and X axis (according to quadrants)
    double Angle = 0;
    
    if ((P1x == P2x) && (P1y == P2y))// same points
    return 0;
    else if (P1x == P2x) // 90 or 270
    {
      Angle = Math.PI / 2;
      if (P1y > P2y) Angle += Math.PI;
    }
    else if (P1y == P2y) // 0 or 180
    {
      Angle = 0;
      if (P1x > P2x) Angle += Math.PI;
    }
    else
    {
      Angle = Math.atan(Math.abs((P2y - P1y) / (P2x - P1x))); // 1. quadrant
      if (P1x > P2x && P1y < P2y) // 2. quadrant
      Angle = Math.PI - Angle;
      else if (P1x > P2x && P1y > P2y) // 3. quadrant
      Angle += Math.PI;
      else if (P1x < P2x && P1y > P2y) // 4. quadrant
      Angle = 2 * Math.PI - Angle;
    }
    return Angle;
  }
  
  protected double PointDistance(double P1x, double P1y, double P2x, double P2y)
  {
    /* Part of ArcCenter */
    return Math.sqrt(Math.pow((P2x - P1x), 2) + Math.pow((P2y - P1y), 2));
  }  
  
  
  /**
  * @param angLineX - Angled Line X
  * @param angLineY - Angled Line Y
  * @param arcRad - Arc Radius
  * @return Arc EndPt X, EndPt Y, Angled Line Angle
  */
  
  /*
  public double[] filletPoint(double angLineX, double angLineY, double arcRad) {
  /* General fillet type function that finds the End Point of the ARC to an angled Line
  * Currently has to work with arcDxf - only ARC function that is 100%
  * TG 21/08/2016  
  *  /
  double rtnArray[] = {0, 0, 0};
  double lineAng = 0;
  //    angLineX = 16.5456; // X move of the flap
  //    angLineY = 9.769; // Y move of the flap
  lineAng = Math.toDegrees(Math.atan2(angLineX, angLineY)); 
  if (lineAng > 45) {
  lineAng = (90 - lineAng);
  }
  System.out.println("Angle = " + String.valueOf(lineAng));
  double o = arcRad * Math.sin(Math.toRadians(lineAng));
  double a = arcRad * Math.cos(Math.toRadians(lineAng));
  System.out.println("X  > " + String.valueOf(o));
  System.out.println("Y  > " + String.valueOf(a));
  
  rtnArray[0] = o;
  rtnArray[1] = a;
  rtnArray[2] = lineAng;
  
  return rtnArray;
  } // filletPoint
  */ 
  
  /**
  * 
  * @param angLineX 
  * @param angLineY
  * @param rad Radius
  * @return
  */
  public static double[] findFilletPoints(double angLineX, double angLineY, double rad) {
    /* Finds Fillet points of an Arc on angled line, when filleting 2 lines - One Straight
    * TG - 26/08/2016
    */
    double rtnAry[] = {0, 0, 0};
    
    double lineAngle = Math.toDegrees(Math.atan2(angLineX, angLineY)); 
    rtnAry[0] = rad * Math.sin(Math.toRadians(lineAngle)); // Opposite (X)
    rtnAry[1] = rad * Math.cos(Math.toRadians(lineAngle)); // Adjacent (Y)
    rtnAry[2] = lineAngle; // Angle of Line / Angle of Arc hit point () 
    if (lineAngle > 45) {
      rtnAry[0] = rad - rtnAry[0];
      // Adjacent = IS OK
    }   
    
    return rtnAry; // 0=YendPt = this.topOtrRad - arcDat[0] , 1=XendPt , 2=endAngle = 360 - arcDat[2]
  } // findFilletPoints  
  
  
  public void drwText(String textToDrw, double txtSz) {
    
    dxf +="  0" + cr;
    dxf +="TEXT" + cr;
    dxf +="  5" + cr; //  Drawing Datadbase Handle
    dxf +=hndl + cr;
    dxf +="  8" + cr; //  Layer name
    dxf += "Design" + cr; // layer + cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += "20" + cr;
    
    dxf +=" 10" + cr; //  Start X
    dxf += xabs + cr;
    dxf +=" 20" + cr; //  Start Y
    dxf += yabs + cr;
    dxf +=" 30" + cr; //  Start Z
    dxf +="0.0" + cr;
    dxf +=" 40" + cr; //  Text Size
    dxf += String.valueOf(txtSz) + cr;
    dxf +=" 1" + cr; //  End Y
    dxf += textToDrw + cr;
    //  Finish
    //      xabs = xabs + xval * Xaxis;
    //      yabs = yabs + yval * Yaxis;
    hndl = hndl + 1;    
    
  } // drwText
  
  // end methods
} // end of dxf12objects
