/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Dao;

import clientDaoInterface.Signable;



/**
 *
 * @author xabig
 */
public class DaoFactory {
    
    public Signable getDao(){
        
        return new DaoImplementation();
    }
}
