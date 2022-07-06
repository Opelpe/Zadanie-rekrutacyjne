package com.pepe.rekrutacjagopos.data.remote.token;

import com.pepe.rekrutacjagopos.data.remote.token.TokenRemoteDataSource;

import javax.inject.Inject;

public class TokenRepository {

    private final TokenRemoteDataSource tokenRemoteDataSource;

    @Inject
    public TokenRepository(TokenRemoteDataSource tokenRemoteDataSource) {
        this.tokenRemoteDataSource = tokenRemoteDataSource;
    }

    public void getToken(){
        tokenRemoteDataSource.getToken();
    }
}
