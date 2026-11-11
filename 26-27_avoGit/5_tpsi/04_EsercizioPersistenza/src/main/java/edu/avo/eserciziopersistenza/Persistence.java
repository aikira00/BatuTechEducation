/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.eserciziopersistenza;

import java.util.List;

/**
 *
 * @author MULTI01
 */
public interface Persistence {
    public boolean loginOk(String username, String password);
    public boolean saveText(String text, String username);
    public List<String> getTextList(String username);
}
