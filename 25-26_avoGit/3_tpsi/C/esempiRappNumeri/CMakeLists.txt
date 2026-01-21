cmake_minimum_required(VERSION 4.0)
project(esempioIntero C)

set(CMAKE_C_STANDARD 11)

add_executable(esempioIntero esempioRappresentazione.c
        esempioOverflow.c
        esempioRappNumeriUnsignedSigned.c
        ieee754/floatingPointMess.c
        ieee754/propDistr.c)
