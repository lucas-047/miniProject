package com.library.Config;

import com.library.dao.AdvanceConfigRepository;
import com.library.entities.AdvanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AdvanceConfigService {
        @Autowired
        private AdvanceConfigRepository advanceConfigRepository;
    public int getPenaltyValue()
    {
       AdvanceConfig advanceConfig= advanceConfigRepository.findById(1);
       if(advanceConfig==null)
       {
           advanceConfig.setId(1);
           advanceConfig.setValue("10");
           advanceConfigRepository.saveAndFlush(advanceConfig);
           return 10;
       }
       else
       {
       int id=Integer.parseInt(advanceConfig.getValue());
        System.out.println("id is "+advanceConfig);
        return id;
       }
    }


        public void setPenaltyValue(String penaltyValue)

        {      AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(1);

            if(advanceConfigcheck==null)

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
        AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(2);

        if(advanceConfigcheck==null)

        {

            AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(2);
            advanceConfig.setValue(value);
            advanceConfigRepository.saveAndFlush(advanceConfig);
        }
        else {

            // AdvanceConfig advanceConfig=new AdvanceConfig();

            AdvanceConfig advanceConfig = advanceConfigRepository.findById(1);
            advanceConfig.setValue(value);
            System.out.println(advanceConfig);
            advanceConfigRepository.saveAndFlush(advanceConfig);

        }
    }
    public int getNumberOfIssueBookForUser()
    {
        AdvanceConfig advanceConfig= advanceConfigRepository.findById(2);
        if(advanceConfig==null)
        {
            advanceConfig.setId(2);
            advanceConfig.setValue("5");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 5;
        }
        else
        {
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }
    public void setNumberOfIssueBookForFaculty(String value)
    {

        AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(3);

        if(advanceConfigcheck==null)

        {

            AdvanceConfig advanceConfig=new AdvanceConfig();
            advanceConfig.setId(3);
            advanceConfig.setValue(value);
            advanceConfigRepository.saveAndFlush(advanceConfig);
        }
        else {

            // AdvanceConfig advanceConfig=new AdvanceConfig();

            AdvanceConfig advanceConfig = advanceConfigRepository.findById(1);
            advanceConfig.setValue(value);
            System.out.println(advanceConfig);
            advanceConfigRepository.saveAndFlush(advanceConfig);

        }
    }
    public int getNumberOfIssueBookForFaculty()
    {
        AdvanceConfig advanceConfig= advanceConfigRepository.findById(3);
        if(advanceConfig==null)
        {
            advanceConfig.setId(3);
            advanceConfig.setValue("10");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 10;
        }
        else
        {
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }
    public void setUserDueDate(String value)
    {
        AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(4);

        if(advanceConfigcheck==null)

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
        AdvanceConfig advanceConfig= advanceConfigRepository.findById(3);
        if(advanceConfig==null)
        {
            advanceConfig.setId(4);
            advanceConfig.setValue("5");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 5;
        }
        else
        {
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }
    public void setFacultyDueDate(String value)
    {
        AdvanceConfig advanceConfigcheck=advanceConfigRepository.findById(5);

        if(advanceConfigcheck==null)

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
        AdvanceConfig advanceConfig= advanceConfigRepository.findById(3);
        if(advanceConfig==null)
        {
            advanceConfig.setId(5);
            advanceConfig.setValue("180");
            advanceConfigRepository.saveAndFlush(advanceConfig);
            return 180;
        }
        else
        {
            int id=Integer.parseInt(advanceConfig.getValue());
            System.out.println("id is "+advanceConfig);
            return id;
        }
    }

}
