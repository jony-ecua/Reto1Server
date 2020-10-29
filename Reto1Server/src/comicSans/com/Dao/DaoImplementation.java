/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Dao;

import clientDaoInterface.Signable;
import user.User;



/**
 *
 * @author xabig
 */
public class DaoImplementation implements Signable{
    
    public void signIn(User user){
        System.out.println(user.getLogin()+" "+user.getPassword()); 
    }
    
    public void signUp(User user){
        
    }    
}
