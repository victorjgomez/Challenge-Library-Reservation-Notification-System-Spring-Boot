package com.library.library.reservation;

import com.library.library.borrow.Borrow;
import com.library.library.member.Member;
import com.library.library.notification.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final NotificationService notificationService;

    public ReservationService(ReservationRepository reservationRepository, NotificationService notificationService) {
        this.reservationRepository = reservationRepository;
        this.notificationService = notificationService;
    }

    public Reservation createReservation(Reservation reservation){
        return this.reservationRepository.save(reservation);
    }

    public Reservation callPendingNotifications(Borrow borrow){
        List<Reservation> reservations = this.reservationRepository.findByBookAndNotifiedAndOrderByDateTimeDesc(borrow.getBook(), false);

        if(reservations.isEmpty()){
            return null;
        }

        Reservation reservation = reservations.getFirst();
        this.notificationService.notifyByReservation(reservation);

        reservation.setNotified(true);

        return reservation;
    }

    public List<Reservation> returnPendingNotifications(Member member){
        return this.reservationRepository.findByMemberAndNotifiedAndOrderByDateTimeDesc(member, false);
    }


}
