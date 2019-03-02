package com.example.moviedb_37.screen.moviedetails;

import com.example.moviedb_37.screen.actors.ActorsNavigator;
import com.example.moviedb_37.screen.movieinfo.InfoNavigator;
import com.example.moviedb_37.screen.producer.ProducerNavigator;
import com.example.moviedb_37.screen.trailer.TrailerNavigator;

public interface MovieDetailNavigator extends InfoNavigator, ActorsNavigator,
        ProducerNavigator, TrailerNavigator {
    void back();
}
