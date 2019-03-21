import { ChatActions, ChatActionTypes } from '@App/chat/actions/types';
import { Message } from '@App/chat/models';

export interface ChatState {
    loading: boolean;
    messages: Message[];
}

export const initialChatState: ChatState = {
    loading: false,
    messages: [],
};

export default (state: ChatState = initialChatState, action: ChatActions): ChatState => {
    switch (action.type) {
        case ChatActionTypes.LoadChatRequest:
            return {
                ...state,
                loading: true,
                messages: [],
            };
        case ChatActionTypes.LoadChatSuccess:
            return {
              ...state,
              loading: false,
              messages: action.payload.messages,
            };
        case ChatActionTypes.LoadChatFailure:
            return {
                ...state,
                loading: false,
            };
        default:
            return state;
    }
};
