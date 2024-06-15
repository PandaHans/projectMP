package com.mp.projectmp.helper;

import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Gebruiker;
import com.mp.projectmp.base.Project;

import java.time.LocalDate;

public interface UserInputInterface {
    Client selectClient(Gebruiker gebruiker);
    Project selectProject(Client client);
    float getFloatInput(String prompt);
    String getStringInput(String prompt);
    LocalDate getDate();
}