package com.library.Config;

import com.library.dao.PenaltyRepository;
import com.library.entities.Penalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class PenaltyService {
    @Autowired
    PenaltyRepository penaltyRepository;

    public int saveTempIssueTransaction(String userid, int bookid, LocalDate issuedate, LocalDate duedate)
    {    Penalty penalty =new Penalty();
        penalty.setTempUserId(userid);
        penalty.setTempBookId(bookid);
        penalty.setTempIssueDate(issuedate);
        penalty.setTempDueDate(duedate);
        penalty.setTempPenaltyStatus(-1);
        penaltyRepository.saveAndFlush(penalty);

        return 0;

    }
    public int savePentaltyStatus(int bookid,int penalty)
    {
       // System.out.println("data is "+s);
                    Optional<Penalty> changeStatus=penaltyRepository.findById(bookid);


        //int i=p.getTempBookId();
      //  System.out.println("getbyid is  "+i);
        if (changeStatus.isPresent()) {
            Penalty changingStatus=changeStatus.get();
            changingStatus.setTempPenaltyStatus(penalty);
            penaltyRepository.saveAndFlush(changingStatus);
        } else {
            System.out.println("Entity with ID   not found.");
        }


      //  Penalty penalty1= penaltyRepository.getdatabyid(bookid);


        return 0;
    }
}
