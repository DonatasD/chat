import { Message } from '@App/chat/models';
import { Action } from 'redux';

export enum ChatActionTypes {
    LoadChatRequest = 'LOAD_CHAT_REQUEST',
    LoadChatSuccess = 'LOAD_CHAT_SUCCESS',
    LoadChatFailure = 'LOAD_CHAT_FAILURE',

    MessageCreated = 'MESSAGE_CREATED',
}

export interface ChatLoadActionPayload {
    messages: Message[];
}

export interface MessageCreatedActionPayload {
    message: Message;
}

export interface ChatLoadRequestAction extends Action {
    readonly type: ChatActionTypes.LoadChatRequest;
}

export interface ChatLoadSuccessAction extends Action {
    readonly type: ChatActionTypes.LoadChatSuccess;
    readonly payload: ChatLoadActionPayload;
}

export interface ChatLoadFailureAction extends Action {
    readonly type: ChatActionTypes.LoadChatFailure;
}

export interface MessageCreatedAction extends Action {
    readonly type: ChatActionTypes.MessageCreated;
    readonly payload: MessageCreatedActionPayload;
}

export type ChatActions =
    | ChatLoadRequestAction
    | ChatLoadSuccessAction
    | ChatLoadFailureAction
    | MessageCreatedAction;
