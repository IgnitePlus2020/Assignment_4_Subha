package com.tgt.igniteplus;
import java.util.*;
import java.lang.Integer;
public class MainMenu {
    public static void main(String[] args) {
        char choice;
        Scanner in=new Scanner(System.in);
        int option;

        Map<String,List<IgniteMembers>> map = new HashMap<>();
        List<String> departments=new ArrayList<>();

        do {
            System.out.println("\n\nMain menu\n");
            System.out.println("1.Create a department");
            System.out.println("2.To display all the departments");
            System.out.println("3.Delete a department");
            System.out.println("4.Display all the members as per department");
            System.out.println("5.Create a member and add the member to a department");
            System.out.println("6.Swapping of Ignite Members from one dept to another dept");
            System.out.println("7.Add a new Skillset to all the Ignite members for the specific dept");
            System.out.println("8.List all the users who have the Skill of “Java” with department name");
            System.out.println("9.Exit");
            System.out.println("\nEnter your option:");
            option=in.nextInt();
            in.nextLine();
            switch (option)
            {
                case 1: System.out.println("\nEnter the name of the department to be created:");
                        String dept=in.nextLine();
                        map.put(dept,null);
                        departments.add(dept);
                        System.out.println("\nDepartment created.");
                        break;
                case 2: System.out.println("Departments");
                        System.out.println("-----------------------------");
                        for(String d:map.keySet())
                            System.out.println(d);
                        break;
                case 3: System.out.println("\nChoose the department to be deleted(Enter number):");
                        for(String s: departments)
                        {
                            System.out.println((departments.indexOf(s)+1)+". "+s);
                        }
                        int num=in.nextInt();
                        in.nextLine();
                        map.remove(departments.get(num-1));
                        System.out.println("\nDepartment deleted.");
                        departments.remove(num-1);
                        break;
                case 4:
                        /* to ensure that atleast one member exists in any department */
                        int flag=0;
                        for (String dep: map.keySet())
                         { if(map.get(dep)==null) {
                                System.out.println("Department " + dep + " has no member. " +
                                     "Either delete this department or create a member and add to this department in order to display all " +
                                        "the departments and its members. ");
                                flag=1;
                                break;
                              }
                         }
                        if(flag==1)
                            break;
                        for (String dep: map.keySet()) {
                        System.out.println("Department Name: " + dep);
                        System.out.println("-----------------------------------");
                        for (IgniteMembers i : map.get(dep))
                            System.out.println(i.toString());
                        System.out.println("***********************************");
                       }
                        break;
                case 5: int exist=0;
                        System.out.println("Enter the name of the member:");
                        String name=in.nextLine();
                        System.out.println("Enter the age of the member:");
                        int age=in.nextInt();
                        in.nextLine();
                        System.out.print("Enter the college:");
                        String college=in.nextLine();
                        System.out.println("Enter the skillset:(type \'ok\' at last)");
                        Set<String> skillSet= new HashSet<>();
                        String s;

                        while(!(s=in.nextLine()).equalsIgnoreCase("ok"))
                        {
                            skillSet.add(s);
                        }
                        System.out.println("\nChoose the department of the new member(Enter number):");
                        for(String d_name: departments)
                        {
                            System.out.println((departments.indexOf(d_name)+1)+". "+d_name);
                        }
                        int n=in.nextInt();
                        in.nextLine();
                        if(map.get(departments.get(n-1))==null)
                        {
                            List<IgniteMembers> temp=new ArrayList<>();
                            temp.add(new IgniteMembers(name,college,age,skillSet));
                            map.put(departments.get(n-1),temp);
                        }
                        else {
                            /* to ensure that the members of a particular department have unique names*/
                            for (IgniteMembers i : map.get(departments.get(n-1))) {
                                if (i.getName().equalsIgnoreCase(name))
                                {
                                    System.out.println(name+" already exists in department "+ departments.get(n-1));
                                    System.out.println("Hence,this record cannot be added.");
                                    exist=1;
                                    break;
                                }
                            }
                            if(exist==1)
                                break;
                            List<IgniteMembers> temp = map.get(departments.get(n - 1));
                            temp.add(new IgniteMembers(name, college, age, skillSet));
                        }
                        System.out.println("Member has been added to the department.");
                        break;
                case 6: System.out.println("\nDepartments :");
                        for(String d_name: departments)
                        {
                            System.out.println((departments.indexOf(d_name)+1)+". "+d_name);
                        }
                        System.out.println("\nChoose the current department of the member(Enter number):");
                        int old=in.nextInt();
                        System.out.println("\nChoose the new department of the member(Enter number):");
                        int newDept=in.nextInt();
                        in.nextLine();
                        System.out.println("\nEnter the name of the member:");
                        name=in.nextLine();
                        String dep=departments.get(old - 1);

                        Iterator<IgniteMembers> iter = map.get(dep).iterator();
                        while(iter.hasNext()){
                            IgniteMembers im = iter.next();
                            if( im.getName().equalsIgnoreCase(name)){
                                List<IgniteMembers> temp1 =map.get(departments.get(newDept-1));
                                temp1.add(im);
                                iter.remove();
                            }
                        }
                        System.out.println("Data updated.");

                        break;
                case 7: System.out.println("\nChoose the department to update the skill set of its members:");
                        for(String d_name: departments)
                        {
                            System.out.println((departments.indexOf(d_name)+1)+". "+d_name);
                        }
                        n=in.nextInt();
                        in.nextLine();
                        dep=departments.get(n - 1);
                        for (IgniteMembers i : map.get(dep))
                        {  System.out.println("Enter the new skillset of "+i.getName()+":");
                           String newskill=in.nextLine();
                           Set<String> currentSkill=i.getSkillSet();
                           currentSkill.add(newskill);
                           i.setSkillSet(currentSkill);

                        }
                        System.out.println("Skill set updated.");
                        break;
                case 8: flag=0;
                        for (String depart: map.keySet())
                        {
                            for (IgniteMembers i : map.get(depart))
                            {
                                if(i.getSkillSet().contains("Java") || i.getSkillSet().contains("java") )
                                {   flag=1;
                                    System.out.println("Department-"+depart+"\tMember-"+i.getName());
                                }
                            }
                        }
                        if(flag==0)
                            System.out.println("No such member having java as skillset in any department.");
                       break;
                case 9:return;
                default:System.out.println("\nWrong option");
            }

            System.out.println("\nReturn to main menu?(y/n)");
            choice=in.next().charAt(0);
        } while (choice == 'y' || choice == 'Y');


    }
}

