package com.smartship.service;

import com.smartship.exception.*;
import com.smartship.model.Agent;
import com.smartship.model.PackageItem;
import com.smartship.model.Route;

public interface DeliveryService {
    void addAgent(Agent a) throws DuplicateEntryException;
    void addRoute(Route r) throws DuplicateEntryException;
    void addPackage(PackageItem p) throws DuplicateEntryException, InvalidPackageException;
    void assignAllPackages() throws Exception;
    void displayStatus();
}
