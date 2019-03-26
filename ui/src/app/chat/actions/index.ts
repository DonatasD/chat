import {
    ChatActionTypes,
    ChatLoadFailureAction,
    ChatLoadRequestAction,
    ChatLoadSuccessAction,
    MessageCreatedAction,
} from '@App/chat/actions/types';
import { Message } from '@App/chat/models';

export const loadChatRequest = (): ChatLoadRequestAction => ({
    type: ChatActionTypes.LoadChatRequest,
});

export const loadChatSuccess = (messages: Message[]): ChatLoadSuccessAction => ({
    payload: {
        messages,
    },
    type: ChatActionTypes.LoadChatSuccess,
});

export const loadChatFailure = (): ChatLoadFailureAction => ({
    type: ChatActionTypes.LoadChatFailure,
});

export const messageCreated = (message: Message): MessageCreatedAction => ({
    payload: {
        message,
    },
    type: ChatActionTypes.MessageCreated,
});
