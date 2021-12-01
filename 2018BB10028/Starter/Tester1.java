public class Tester1 {
  public static void main(String[] args){

    int score = 0;
    int total  = 0;
    int  N = 17;
    OrgHierarchyInterface org = new OrgHierarchy();
    System.out.println("\n--Initialized an Empty Tree--");
    //isEmpty()
    System.out.println("\nIs Tree Empty- " + org.isEmpty());
    
    //hireowner
     try{
    org.hireOwner(1);
    System.out.println("Hired Owner successfully with id = 1" );
    }
    catch(NotEmptyException e)
    {
      System.out.println(e);
    }
    
    //isEmpty()
    System.out.println("\nIs Tree Empty- "+ org.isEmpty()+"\n");
    
    
    // catch notempty exception
    System.out.println("\n--Trying to hire owner with id = 2--");
    try{
    org.hireOwner(2);
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
                            1
                  /           \        \
                3            2          12
              /   \          \           \
            8     4           7           10
          /       |         /   \   \     
        16        5       100   17  110
                /        /  \          
              6         99    89
                              |
                              55
    */

    //insert employees
    try{
    org.hireEmployee(3,1);
    System.out.println("\nHired employee successfully with id = 3" );
    org.hireEmployee(2,1);
    System.out.println("Hired employee successfully with id = 2" );
    org.hireEmployee(12,1);
    System.out.println("Hired employee successfully with id = 12" );
    org.hireEmployee(8,3);
    System.out.println("Hired employee successfully with id = 8" );
    org.hireEmployee(4,3);
    System.out.println("Hired employee successfully with id = 4" );
    org.hireEmployee(7,2);
    System.out.println("Hired employee successfully with id = 7" );
    org.hireEmployee(10,12);
    System.out.println("Hired employee successfully with id = 10" );
    org.hireEmployee(16,8);
    System.out.println("Hired employee successfully with id = 16" );
    org.hireEmployee(5,8);
    System.out.println("Hired employee successfully with id = 5\n" );

    org.hireEmployee(6,5);
    System.out.println("Hired employee successfully with id = 6" );
    org.hireEmployee(100,7);
    System.out.println("Hired employee successfully with id = 100" );
    org.hireEmployee(110,7);
    System.out.println("Hired employee successfully with id = 110" );
    org.hireEmployee(17,7);
    System.out.println("Hired employee successfully with id = 17" );
    org.hireEmployee(99,100);
    System.out.println("Hired employee successfully with id = 99" );
    org.hireEmployee(89,100);
    System.out.println("Hired employee successfully with id = 89" );
    org.hireEmployee(55,89);
    System.out.println("Hired employee successfully with id = 55" );



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
    System.out.println("\nOrganization = "+org.toString(1));
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
      System.out.println("\nWhat is the level of employee 89?" );
      int l = org.level(89); 
      System.out.println("Level = "+l);
     
      if(l != 5) {
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
        System.out.println("\n--Trying to fire employee with id = 110--");
        org.fireEmployee(110);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+size1);
        System.out.println("Updated Organization = "+ org.toString(1));
        
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
        System.out.println("\n-- fire employee with id = 2 and make id = 12 new boss of its children--");
        org.fireEmployee(2,12);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+ size1);
        System.out.println("New Tree- "+ org.toString(1));
        System.out.println("Subtree rooted at 1- "+ org.toString(1));
        //check if boss is changed after deletion
        int boss1 = org.boss(7);
       
        if(boss1 != 12){
          System.out.println("test5 ---------------> FAIL");
        }
        else{
          score+=3;
          System.out.println("--Employee 12 is made new boss successfully--");
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
    org.hireEmployee(2,1);
    System.out.println("\nHired employee successfully with id = 2" );
    org.hireEmployee(110,3);
    System.out.println("Hired employee successfully with id = 110" );

    if(org.toString(3).equals("3,4 8 110,5 16,6")){
      score+=4;
      System.out.println("test6-----------------------> PASS\n\n");
    }
      else System.out.println("test6-----------------------> FAIL\nyour tree at 3: "+org.toString(3)+"\nCorrect tree at 3: 3,4 8 110,5 16,6");
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

try{
    org.hireEmployee(77,12);
    System.out.println("\nHired employee successfully with id = 77" );
    score+=3;
    System.out.println("test7-----------------------> PASS");
    // System.out.println("test7 --------------------------> FAIL\n3 can't be hired, its already in the organization\n\n");
}
catch(IllegalIDException e){

      
      System.out.println(e);
     }
    catch(EmptyTreeException e)
    { 
    System.out.println(e);
    }







/*************************************************** */
total+=7;

 try{
        System.out.println("\n-- fire employee with id = 100 and make id = 17 new boss of its children--");
        org.fireEmployee(100,17);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+ size1);
        System.out.println("New Tree- "+ org.toString(1));
        System.out.println("Subtree rooted at 12- "+ org.toString(12));
        //check if boss is changed after deletion

        if(org.toString(1).equals("1,2 3 12,4 7 8 10 110,5 16 17,6 89 99,55")){
      score+=7;
      System.out.println("test8-----------------------> PASS");
    }
      else System.out.println("test8-----------------------> FAIL\n your organization is : "+org.toString(1)+"\nCorrect tree : 1,2 3 12,4 7 8 10 110,5 16 17,6 89 99,55");
    }

    catch (IllegalIDException e) {
        System.out.println("Employee does not exist");
      }
  
     catch(EmptyTreeException e){ 
      System.out.println(e);
    }   






  total+=3;
  try{
        int boss1 = org.boss(89);
        if(boss1 != 17){
          System.out.println("test9 --------------------> FAIL");
        }
        else{
          score+=3;
          System.out.println("--Employee 17 is made new boss successfully--");
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
    	System.out.println("\n--Find lowest common boss of employees 55 and 10--");
        int lcb = org.lowestCommonBoss(10, 55);
        System.out.println("your answer = "+lcb);
        if(lcb != 12){
          System.out.println("tes10 -------------------> FAIL");
          System.out.println("lowest common boss is 12");
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
