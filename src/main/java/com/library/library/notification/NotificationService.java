package com.library.library.notification;


import com.library.library.borrow.Borrow;
import com.library.library.borrow.BorrowService;
import com.library.library.reservation.Reservation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final BorrowService borrowService;

    public NotificationService(BorrowService borrowService) {
        this.borrowService = borrowService;
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void notifyByDueDay(){
        // run everyday check the filter the list of borrows that are due for two days

        List<Borrow> borrowList = borrowService.getAllByRule();

        for (Borrow borrow: borrowList){
            System.out.println("Sending notification member with Id:" + borrow.getMember().getId());
        }

    }

    public void notifyByReservation(Reservation reservation){
        if(reservation != null){
            System.out.println("Sending notification to member with Id:" + reservation.getMember().getId());

        }
    }
}
