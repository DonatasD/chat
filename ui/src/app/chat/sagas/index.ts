import { loadChatSuccess } from '@App/chat/actions';
import { ChatActionTypes } from '@App/chat/actions/types';
import { Message } from '@App/chat/models';
import { call, put, takeLatest } from '@redux-saga/core/effects';

const fetchMessages = () => {
    return fetch(`http://localhost:8080/api/messages`)
        .then(response => response.json())
        .catch(reason => reason);
};

export function* loadChatRequestSaga() {
    const messages: Message[] = yield call(fetchMessages);
    yield put(loadChatSuccess(messages));
}

export const chatSagas = [
    takeLatest(ChatActionTypes.LoadChatRequest, loadChatRequestSaga),
];
