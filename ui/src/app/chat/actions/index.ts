import { ChatActionTypes, ChatLoadRequestAction, ChatLoadSuccessAction } from '@App/chat/actions/types';
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
