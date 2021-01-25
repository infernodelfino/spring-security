1, project được chia làm 6 folder chính
- config: Dành cho config chung về security của project
- controller: End point nhưng không dùng...
- entities:
- repository: Repository cho các Entity
- security: Cho các component mình modify riêng của Spring Security
    + authentication: Chưa hiểu chính xác là gì nhưng class này sẽ dành cho mỗi Entity, và implements interface con của Authentication
    + filter: Class modify filter, extends OncePerRequestFilter. Override lại 2 methods doFilterInternal() và shouldNotFilter()
    + model: Là class có chứa 1 variable kiểu 1 entity và implements interface UserDetails
    + provider: Authentication Provider là 1 provider được định nghĩa riêng cho mỗi Entity, implement lại 1 AuthenticationProvider interface rồi override lại 2 phương thức.
    Như ở bài này, tầng này dùng call Service/Repository để check username/otp có trong DB chưa? Rồi xử lý logic
- service: Gọi Repository.