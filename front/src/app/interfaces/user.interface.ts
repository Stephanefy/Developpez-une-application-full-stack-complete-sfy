export interface User {
  userId: number;
  sub: string;
  username: string;
  email: string;
}

export interface StoredUser extends User {
  exp: number;
  iat: number;
}
