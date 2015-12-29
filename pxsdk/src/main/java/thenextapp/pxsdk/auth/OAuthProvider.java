package thenextapp.pxsdk.auth;

import org.apache.http.client.methods.HttpPost;

import thenextapp.pxsdk.FiveHundredException;


public interface OAuthProvider {
	void signForAccessToken(HttpPost req) throws FiveHundredException;
	
	void setOAuthConsumer(String consumerKey, String consumerSecret);
	void setOAuthRequestToken(String requestTokenKey, String requestTokenSecret);
}