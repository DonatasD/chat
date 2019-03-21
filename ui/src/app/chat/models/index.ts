import { ChatState } from '@App/chat/reducers';

export interface Message {
    content: string;
    createdDate: string;
    createdBy: string;
}

export interface ChatContainerActions {
    load: () => any;
}

export interface ChatContainerProps {
    chat: ChatState;
    actions: ChatContainerActions;
}

export interface MessageComponentProps {
    message: Message;
}
