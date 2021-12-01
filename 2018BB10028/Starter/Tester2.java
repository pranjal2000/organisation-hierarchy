public class Tester2 {
  public static void main(String[] args){

    int score = 0;
    int total  = 0;
    int  N = 14;
    OrgHierarchyInterface org = new OrgHierarchy();
    System.out.println("\n--Initialized an Empty Tree--");
    //isEmpty()
    System.out.println("\nIs Tree Empty- " + org.isEmpty());
    
    //hireowner
     try{
    org.hireOwner(4);
    System.out.println("Hired Owner successfully with id = 4" );
    }
    catch(NotEmptyException e)
    {
      System.out.println(e);
    }
    
    //isEmpty()
    System.out.println("\nIs Tree Empty- "+ org.isEmpty()+"\n");
    
    
    // catch notempty exception
    System.out.println("\n--Trying to hire owner with id = 7--");
    try{
    org.hireOwner(7);
    }
    catch(NotEmptyException e)
    {
      score++;
      System.out.println("test1 -----------------------> PASS");
      System.out.println(e);
    }
    total++;
      
    /* 
    Tree-
                    
                              4
                    /         |      \       \
                  10          12      14      16
                /   \       /   \       \       \
              89    90     15    2       3       100
                                /   \               \
                            1000    1100            99


    */

    //insert employees
    try{
    org.hireEmployee(10,4);
    System.out.println("\nHired employee successfully with id = 10" );
    org.hireEmployee(89,10);
    System.out.println("Hired employee successfully with id = 89" );
    org.hireEmployee(12,4);
    System.out.println("Hired employee successfully with id = 12" );
    org.hireEmployee(14,4);
    System.out.println("Hired employee successfully with id = 14" );
    org.hireEmployee(15,12);
    System.out.println("Hired employee successfully with id = 15" );
    org.hireEmployee(2,12);
    System.out.println("Hired employee successfully with id = 2" );
    org.hireEmployee(1000,2);
    System.out.println("Hired employee successfully with id = 1000" );
    org.hireEmployee(1100,2);
    System.out.println("Hired employee successfully with id = 1100" );
    org.hireEmployee(3,14);
    System.out.println("Hired employee successfully with id = 3\n" );

    org.hireEmployee(90,10);
    System.out.println("Hired employee successfully with id = 10" );
    org.hireEmployee(16,4);
    System.out.println("Hired employee successfully with id = 16" );
    org.hireEmployee(100,16);
    System.out.println("Hired employee successfully with id = 100" );
    org.hireEmployee(99,100);
    System.out.println("Hired employee successfully with id = 99" );


    }
    catch(IllegalIDException e){
      System.out.println(e);
     }
    catch(EmptyTreeException e)
    { 
    System.out.println(e);
    }
    //print tree
   
    int size1 = org.size();
    System.out.println("\nNew Organization Size: " + size1);
    try{
    System.out.println("\nOrganization = "+org.toString(4));
    }
    catch (IllegalIDException e) {
        System.out.println(e);
    }
    catch(EmptyTreeException e){ 
      System.out.println(e);
    }
    //check size
    total++;
    if(size1 != N){
      System.out.println("test2 -----------------------> FAIL");
      }
    else{
      score++;
      System.out.println("test2 ---------------------> PASS");
    }
    
    //check level
    total+=2;
    try{
      System.out.println("\nWhat is the level of employee 2?" );
      int l = org.level(2); 
      System.out.println("Level = "+l);
     
      if(l != 3) {
        System.out.println("test3 ---------------------> FAIL");
      }else{
        score+=2;
        System.out.println("test3 --------------------> PASS");
      }
    }
    catch (IllegalIDException e){
      System.out.println(e);
    }
     catch(EmptyTreeException e){ 
      System.out.println(e);
    }

  total +=1;
    //check fireEmployee
      try{
        System.out.println("\n--Trying to fire employee with id = 3--");
        org.fireEmployee(3);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+size1);
        System.out.println("Updated Organization = "+ org.toString(4));
        
        if(size1 != N-1){
          System.out.println("test4------------------------> FAIL");
        }
        else{
          score+=1;
          System.out.println("test4----------------------> PASS");
        }
      }
      catch (IllegalIDException e) {
        System.out.println(e);
      }
      catch(EmptyTreeException e){ 
      System.out.println(e);
    }   
     total+=3;
    //check fireEmployee(id, Manageid)
    try{
        System.out.println("\n-- fire employee with id = 12 and make id = 14 new boss of its children--");
        org.fireEmployee(12,14);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+ size1);
        System.out.println("New Tree- "+ org.toString(4));
        System.out.println("Subtree rooted at 1- "+ org.toString(4));
        //check if boss is changed after deletion
        int boss1 = org.boss(2);
       
        if(boss1 != 14){
          System.out.println("test5 ---------------> FAIL");
        }
        else{
          score+=3;
          System.out.println("--Employee 14 is made new boss successfully--");
          System.out.println("test5 --------------------> PASS");
        }
      }
      catch (IllegalIDException e) {
        System.out.println("Employee does not exist");
      }
  
     catch(EmptyTreeException e){ 
      System.out.println(e);
    }   
    
/************************************************ */
  total+=4;

 try{
    org.hireEmployee(3,4);
    System.out.println("\nHired employee successfully with id = 3" );
    org.hireEmployee(12,3);
    System.out.println("Hired employee successfully with id = 12" );

    if(org.toString(14).equals("14,2 15,1000 1100")){
      score+=4;
      System.out.println("test6-----------------------> PASS\n\n");
    }
      else System.out.println("test6-----------------------> FAIL\nyour tree at 14: "+org.toString(14)+"\nCorrect tree at 14: 4,2 15,1000 1100");
    }
    catch(IllegalIDException e){
      System.out.println(e);
     }
    catch(EmptyTreeException e)
    { 
    System.out.println(e);
    }




/************************************************* */
total+=3;

// try{
//     org.hireEmployee(3,14);
//     System.out.println("\nHired employee successfully with id = 3" );
//     System.out.println("test7 --------------------------> FAIL\n3 can't be hired, its already in the organization\n\n");
// }
// catch(IllegalIDException e){

//       score+=3;
//       System.out.println("test7-----------------------> PASS");
//       System.out.println(e);
//      }
//     catch(EmptyTreeException e)
//     { 
//     System.out.println(e);
//     }







/*************************************************** */
total+=7;

 try{
        System.out.println("\n-- fire employee with id = 14 and make id = 16 new boss of its children--");
        org.fireEmployee(14,16);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+ size1);
        System.out.println("New Tree- "+ org.toString(4));
        System.out.println("Subtree rooted at 16- "+ org.toString(16));
        //check if boss is changed after deletion

        if(org.toString(4).equals("4,3 10 16,2 12 15 89 90 100,99 1000 1100")){
      score+=7;
      System.out.println("test8-----------------------> PASS");
    }
      else System.out.println("test8-----------------------> FAIL\n your organization is : "+org.toString(4)+"\nCorrect tree at 4: 4,3 10 16,2 12 15 89 90 100,99 1000 1100");
    }

    catch (IllegalIDException e) {
        System.out.println("Employee does not exist");
      }
  
     catch(EmptyTreeException e){ 
      System.out.println(e);
    }   






  total+=3;
  try{
        int boss1 = org.boss(15);
        if(boss1 != 16){
          System.out.println("test9 --------------------> FAIL");
          System.out.println(boss1+ " This is the boss");
        }
        else{
          score+=3;
          System.out.println("--Employee 16 is made new boss successfully--");
          System.out.println("test9 --------------------->PASS");
        }
  }

  catch (IllegalIDException e) {
        System.out.println("Employee does not exist");
      }
  
     catch(EmptyTreeException e){ 
      System.out.println(e);
    }   





/****************************************************** */
total+=5;

    //check lowest common boss-
    try{
    	System.out.println("\n--Find lowest common boss of employees 1000 and 90--");
        int lcb = org.lowestCommonBoss(90, 1000);
        System.out.println("your answer = "+lcb);
        if(lcb != 4){
          System.out.println("tes10 -------------------> FAIL");
          System.out.println("lowest common boss is 4");
        }
        else{
          score+=5;
          System.out.println("test10 --------------------> PASS");
        }
      }
      catch (IllegalIDException e) {
        System.out.println("Employee does not exist");
      }
      catch(EmptyTreeException e){ 
      System.out.println("Owner Already Exists!");
    }
      System.out.println("\n\nscore: "+score+" : " + total );

  }
}
