import { chatSagas } from '@App/chat/sagas';
import { all } from '@redux-saga/core/effects';

export default function* rootSaga() {
    yield all([
        ...chatSagas,
    ]);
}
