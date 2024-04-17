package com.library.Config;

import com.library.dao.AdvanceConfigRepository;
import com.library.entities.AdvanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
id 1= penalty price
id 2= user issue book
id 3= faculty issue book
id 4=user due date duration
id 5=faculty due date duration
*/


@Service
public class AdvanceConfigService {
        @Autowired
        private AdvanceConfigRepository advanceConfigRepository;
    public int getPenaltyValue()
    {
       boolean Check=advanceConfigRepository.existsById(1);
       if(!Check)
       {AdvanceConfig advanceConfig= new AdvanceConfig();
           advanceConfig.setId(1);
           advanceConfig.setValue("10");
           advanceConfigRepository.saveAndFlush(advanceConfig);
           return 10;
       }
       else
       {AdvanceConfig advanceConfig= advanceConfigRepository.findById(1);
       int id=Integer.parseInt(advanceConfig.getValue());
        System.out.println("id is "+advanceConfig);
        return id;
       }
    }


        public void setPenaltyValue(String penaltyValue)

        {     // AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(1);
            boolean Check=advanceConfigRepository.existsById(1);
            if(!Check)

            {

            AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(1);
            advanceConfig.setValue(penaltyValue);
            advanceConfigRepository.saveAndFlush(advanceConfig);
        }
            else {
//                int id=advanceConfigcheck.getId();
//                if(Integer.toString(id)==null)
//                {
//                    AdvanceConfig advanceConfig=new AdvanceConfig();
//                    advanceConfig.setValue(penaltyValue);
//                    advanceConfigRepository.saveAndFlush(advanceConfig);
//                }
                AdvanceConfig advanceConfig=advanceConfigRepository.findById(1);
                    advanceConfig.setValue(penaltyValue);
                System.out.println(advanceConfig);
                    advanceConfigRepository.saveAndFlush(advanceConfig);
            }


        }
    public void setNumberOfIssueBookForUser(String value)
    {
//        AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(2);
        boolean check= advanceConfigRepository.existsById(2);
        if(!check)

        {

            AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(2);
            advanceConfig.setValue(value);
            advanceConfigRepository.saveAndFlush(advanceConfig);
        }
        else {

            // AdvanceConfig advanceConfig=new AdvanceConfig();

            AdvanceConfig advanceConfig = advanceConfigRepository.findById(2);
            advanceConfig.setValue(value);
            System.out.println(advanceConfig);
            advanceConfigRepository.saveAndFlush(advanceConfig);

        }
    }
    public int getNumberOfIssueBookForUser()
    {
        boolean check= advanceConfigRepository.existsById(2);
        if(!check)
        {AdvanceConfig advanceConfig= new AdvanceConfig();
            advanceConfig.setId(2);
            advanceConfig.setValue("5");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 5;
        }
        else
        {AdvanceConfig advanceConfig= advanceConfigRepository.findById(2);
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }
    public void setNumberOfIssueBookForFaculty(String value)
    {

       // AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(3);
        boolean check=advanceConfigRepository.existsById(3);
        if(!check)

        {

            AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(3);
            advanceConfig.setValue(value);
            advanceConfigRepository.saveAndFlush(advanceConfig);
        }
        else {

            // AdvanceConfig advanceConfig=new AdvanceConfig();

            AdvanceConfig advanceConfig = advanceConfigRepository.findById(3);
            advanceConfig.setValue(value);
            System.out.println(advanceConfig);
            advanceConfigRepository.saveAndFlush(advanceConfig);

        }
    }
    public int getNumberOfIssueBookForFaculty()
    {  boolean checkadvanceConfig=advanceConfigRepository.existsById(3);
       // AdvanceConfig advanceConfig= advanceConfigRepository.findById(3);
        if(!checkadvanceConfig)
        {   AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(3);
            advanceConfig.setValue("10");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 10;
        }
        else
        {   AdvanceConfig advanceConfig=advanceConfigRepository.findById(3);
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }
    public void setUserDueDate(String value)
    {
        //AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(4);
        boolean check= advanceConfigRepository.existsById(4);
        if(!check)

        {

            AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(4);
            advanceConfig.setValue(value);
            advanceConfigRepository.saveAndFlush(advanceConfig);
        }
        else {

            // AdvanceConfig advanceConfig=new AdvanceConfig();

            AdvanceConfig advanceConfig = advanceConfigRepository.findById(4);
            advanceConfig.setValue(value);
            System.out.println(advanceConfig);
            advanceConfigRepository.saveAndFlush(advanceConfig);

        }
    }
    public int getUserDueDate()
    {
       boolean check= advanceConfigRepository.existsById(4);
        if(!check)
        { AdvanceConfig advanceConfig= new AdvanceConfig();
            advanceConfig.setId(4);
            advanceConfig.setValue("5");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 5;
        }
        else
        { AdvanceConfig advanceConfig= advanceConfigRepository.findById(4);
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }
    public void setFacultyDueDate(String value)
    {
//        AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(5);
            boolean check= advanceConfigRepository.existsById(5);
        if(!check)

        {

            AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(5);
            advanceConfig.setValue(value);
            advanceConfigRepository.saveAndFlush(advanceConfig);
        }
        else {

            // AdvanceConfig advanceConfig=new AdvanceConfig();

            AdvanceConfig advanceConfig = advanceConfigRepository.findById(5);
            advanceConfig.setValue(value);
            System.out.println(advanceConfig);
            advanceConfigRepository.saveAndFlush(advanceConfig);

        }
    }

    public int getFacultyDueDate()
    {
       boolean check=advanceConfigRepository.existsById(5);
        if(!check)
        { AdvanceConfig advanceConfig= new AdvanceConfig();
            advanceConfig.setId(5);
            advanceConfig.setValue("180");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 180;
        }
        else
        { AdvanceConfig advanceConfig= advanceConfigRepository.findById(5);
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }

}
