import chatReducer, { ChatState } from '@App/chat/reducers';
import { connectRouter, RouterState } from 'connected-react-router';
import { History } from 'history';
import { combineReducers } from 'redux';

export interface State {
    chat: ChatState;
    router: RouterState;
}

export default (history: History) => {
    return combineReducers<State>({
        chat: chatReducer,
        router: connectRouter(history),
    });
};
