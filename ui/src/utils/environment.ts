
export enum Environment {
    Production = 'production',
    Development = 'development',
}

export const isProduction = (): boolean => {
    return process.env.NODE_ENV === Environment.Production;
};
