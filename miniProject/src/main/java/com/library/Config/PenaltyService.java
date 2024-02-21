package com.library.Config;

import com.library.dao.PenaltyRepository;
import com.library.entities.Penalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Optional<Penalty> changeStatus=penaltyRepository.findById(bookid);
        Penalty changingStatus=changeStatus.get();
        changingStatus.setTempPenaltyStatus(penalty);
        penaltyRepository.saveAndFlush(changingStatus);

      //  Penalty penalty1= penaltyRepository.getdatabyid(bookid);


        return 0;
    }
}
