package com.sbrf.reboot.repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

public interface AccountRepository {
    Set<Long> getAllAccountsByClientId(long clientId) throws IOException;
    Long getAccountById(long accountId);
}
