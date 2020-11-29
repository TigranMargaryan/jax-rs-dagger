package com.module;

import com.database.MySqlDb;
import dagger.Module;
import dagger.Provides;

@Module
public class MySqlModule {

    @Provides
    public MySqlDb provideMysql(){
        return new MySqlDb();
    }
}
