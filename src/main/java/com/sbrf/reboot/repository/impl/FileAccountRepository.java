package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAccountRepository implements AccountRepository {
    private final String filePath;

    public FileAccountRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) throws IOException {
        Set<Long> allAccounts = new HashSet<>();
        Map<Long, Long> accountsMatching = getAccountsMatching();

        for (Map.Entry<Long, Long> entry : accountsMatching.entrySet()) {
            if (entry.getValue() == clientId) {
                allAccounts.add(entry.getKey());
            }
        }

        return allAccounts;
    }

    @Override
    public Long getAccountById(long accountId) {
        return null;
    }

    private Map<Long, Long> getAccountsMatching() throws IOException {
        Map<Long, Long> accountsMatching = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                String stringLine = reader.readLine();
                if (stringLine.contains("clientId")) {
                    Long clientId = getNumberInfo(stringLine);
                    Long numberAcc = getNumberInfo(reader.readLine());

                    accountsMatching.put(numberAcc, clientId);
                }
            }
        }
        return accountsMatching;
    }

    private long getNumberInfo(String stringLine) {
        long numberInfo = 0L;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(stringLine);

        if (matcher.find()) {
            numberInfo = Long.parseLong(stringLine.substring(matcher.start(), matcher.end()));
        }
        return numberInfo;
    }
}
