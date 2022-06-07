package vn.techmaster.banksimulator.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TransferException extends RuntimeException{
    public TransferException(String message) {
        super(message);
        log.error(message);
    }
}
