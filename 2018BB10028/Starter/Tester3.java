public class Tester3 {
  public static void main(String[] args){

    int score = 0;
    int total  = 0;
    int  N = 4;
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
          /     \
        2       3
                  \
                    4  
    */

    //insert employees
    try{
    org.hireEmployee(3,1);
    System.out.println("\nHired employee successfully with id = 3" );
    org.hireEmployee(2,1);
    System.out.println("Hired employee successfully with id = 2" );
    org.hireEmployee(4,3);
    System.out.println("Hired employee successfully with id = 4" );


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
      System.out.println("\nWhat is the level of employee 3?" );
      int l = org.level(3); 
      System.out.println("Level = "+l);
     
      if(l != 2) {
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
    ++N;
     total+=3;
    //check fireEmployee(id, Manageid)
    try{
        System.out.println("\n-- fire employee with id = 3 and make id = 2 new boss of its children--");
        org.fireEmployee(3,2);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+ size1);
        System.out.println("New Tree- "+ org.toString(1));
        System.out.println("Subtree rooted at 1- "+ org.toString(1));
        //check if boss is changed after deletion
        int boss1 = org.boss(4);
       
        if(boss1 != 2){
          System.out.println("test4 ---------------> FAIL");
        }
        else{
          score+=3;
          System.out.println("--Employee 2 is made new boss successfully--");
          System.out.println("test4 --------------------> PASS");
        }
      }
      catch (IllegalIDException e) {
        System.out.println("Employee does not exist");
      }
  
     catch(EmptyTreeException e){ 
      System.out.println(e);
    }   
    
/************************************************ */




/************************************************* */





/*************************************************** */
total+=5;

 try{
        System.out.println("\n-- fire employee with id = 4 --");
        org.fireEmployee(4);
        System.out.println("\n-- fire employee with id = 2 --");
        org.fireEmployee(2);
        size1 = org.size();
        System.out.println("--Employee fired successfully--");
        System.out.println("Size: "+ size1);
        //check if boss is changed after deletion
        if(org.toString(1).equals("1")){
      score+=5;
      System.out.println("test5-----------------------> PASS");
    }
      else System.out.println("test5-----------------------> FAIL\n your organization is : "+org.toString(1)+"\nCorrect tree at 1: 1");
    }

    catch (IllegalIDException e) {
        System.out.println("Employee does not exist");
      }
  
     catch(EmptyTreeException e){ 
      System.out.println(e);
    }   




/****************************************************** */
      System.out.println("\n\nscore: "+score+" : " + total );

  }
}
