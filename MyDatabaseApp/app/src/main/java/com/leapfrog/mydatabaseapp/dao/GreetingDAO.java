package com.leapfrog.mydatabaseapp.dao;

import com.leapfrog.mydatabaseapp.entity.Greeting;

import java.util.List;

/**
 * Created by Ajit Kumar Baral on 9/1/2015.
 */
public interface GreetingDAO {

    public long insert(Greeting greeting);
    public int update(Greeting greeting);
    public int delete(int id);
    public Greeting getById(int id);
    public List<Greeting> getAll();
}
