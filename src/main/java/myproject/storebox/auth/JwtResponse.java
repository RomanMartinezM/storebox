package myproject.storebox.auth;

public record JwtResponse(
    String token,
    String refreshToken,
    String type,
    Long id,
    String email
) {
    public JwtResponse(String token, String refreshToken, Long id, String email) {
        this(token, refreshToken, "Bearer", id, email);
    }
}
