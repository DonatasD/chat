import { loadChatFailure, loadChatSuccess } from '@App/chat/actions';
import { ChatActionTypes } from '@App/chat/actions/types';
import { Message } from '@App/chat/models';
import { call, put, takeLatest } from '@redux-saga/core/effects';

const fetchMessages = () => {
    return fetch(`http://localhost:8080/api/messages`)
        /*.then(response => {
            if (!response.ok) {
                throw Error(response.statusText);
            }
            return response;
        })*/
        .then(response => response.json())
        .catch(reason => reason);
};

export function* loadChatRequestSaga() {
    try {
        const messages: Message[] = yield call(fetchMessages);
        yield put(loadChatSuccess(messages));
    } catch (e) {
        yield put(loadChatFailure());
    }
}

export const chatSagas = [
    takeLatest(ChatActionTypes.LoadChatRequest, loadChatRequestSaga),
];
