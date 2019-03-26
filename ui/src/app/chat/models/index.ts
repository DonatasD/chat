import { ChatState } from '@App/chat/reducers';
import { Dispatch } from 'redux';

export interface Message {
    content: string;
    createdDate: string;
    createdBy: string;
}

export interface ChatContainerActions {
    load: () => any;
    dispatch: Dispatch;
}

export interface ChatContainerProps {
    chat: ChatState;
    actions: ChatContainerActions;
}

export interface ChatContainerState {
    ws: WebSocket;
}

export interface MessageListComponentProps {
    messages: Message[];
}

export interface MessageComponentProps {
    message: Message;
}

export enum ChatWebSocketEventType {
    MessageCreated = 'MESSAGE_CREATED',
}
