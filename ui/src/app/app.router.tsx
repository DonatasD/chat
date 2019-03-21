import ChatContainer from '@App/chat/chat.container';
import React from 'react';
import { Redirect, Route, Switch } from 'react-router';

const AppRouter = () => {
    return (
        <Switch>
            <Route exact path='/chat' component={ChatContainer}/>
            <Redirect to='chat'/>
        </Switch>
    );
};

export default AppRouter;
