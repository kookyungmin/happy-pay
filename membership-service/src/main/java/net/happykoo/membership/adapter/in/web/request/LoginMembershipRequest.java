package net.happykoo.membership.adapter.in.web.request;

public record LoginMembershipRequest(
    String email,
    String password
) {

}
