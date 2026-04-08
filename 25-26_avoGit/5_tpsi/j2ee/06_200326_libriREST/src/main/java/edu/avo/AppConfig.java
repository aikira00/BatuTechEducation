package edu.avo;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")   // tutti gli endpoint sotto /api/*
public class AppConfig extends Application {
}