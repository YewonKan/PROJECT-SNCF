package fr.pantheonsorbonne.ufr27.miage.exception;

public class UnsuficientQuotaForTripException extends Throwable {
    public UnsuficientQuotaForTripException(int idTrip) { super("not enough quota for trip " + idTrip);
    }
}
