import { Message } from '@App/chat/models';
import moment from 'moment';
import uuid from 'uuid/v1';

export const generateMessage = (
    content: string = 'message',
    createdBy: string = uuid(),
    createdDate: string = moment().toString(),
): Message => {
    return {
        content,
        createdBy,
        createdDate,
    };
};

export const messages = (
    n: number = 10,
    generator = generateMessage(),
): Message[] => Array(n).fill(generator);
