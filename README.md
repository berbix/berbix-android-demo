# berbix-android-demo

Berbix Android Demo app for demo and integration example. This example is meant to be illustrative of how 
Berbix Client could integrate into an Android app. A valid `client_token` is required and can be generated from a 
backend SDK or from the API docs. 

## How to use

Replace `your_token_here` in MainActivity.kt with a valid client token for testing. 

```
startVerificationButton.setOnClickListener {
      val sdk = BerbixSDK()
      val config = BerbixConfigurationBuilder()
          .setClientToken("your_token_here") // fetch token from backend or paste in for demo
          .build()
      sdk.startFlow(this, config)
    }
```

See [Generating development client tokens](https://docs.berbix.com/docs/testing#generating-development-client-tokens-for-client-side-sdks) for more 
information on how to generate a client token for testing and development.  

## Result Handling

In this demo, the `onActivityResult` just displays a Toast message. In a real app, the app would check with a
backend server to determine the verification result and whether to allow the user to proceed with app functionality.

## Full Integration Docs

See the full Android Client SDK Docs [here](https://docs.berbix.com/docs/android). 

## Help

For further help with integrations or issues, please contact support@berbix.com, or via your customer support channel.
