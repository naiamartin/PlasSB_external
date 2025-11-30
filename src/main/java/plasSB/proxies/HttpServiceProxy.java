package plasSB.proxies;

import java.net.http.HttpClient;
import java.util.List;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

import plasSB.entity.Allocation;
import plasSB.entity.Credentials;

public class HttpServiceProxy implements IPlasSBServiceProxy{
	private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    public HttpServiceProxy() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }
    
	@Override
	public String login(Credentials credentials) {
		try {
            String credentialsJson = objectMapper.writeValueAsString(credentials);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(credentialsJson))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return switch (response.statusCode()) {
                case 200 -> response.body(); // Successful login, returns token
                case 401 -> throw new RuntimeException("Unauthorized: Invalid credentials");
                default -> throw new RuntimeException("Login failed with status code: " + response.statusCode());
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error during login", e);
        }
	}

	@Override
	public void logout(String token) {
		try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/auth/logout"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(token))
                .build();

            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            switch (response.statusCode()) {
                case 204 -> {} // Logout successful
                case 401 -> throw new RuntimeException("Unauthorized: Invalid token, logout failed");
                default -> throw new RuntimeException("Logout failed with status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error during logout", e);
        }
	}

	@Override
	public List<Allocation> getAllAllocations() {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + "plant/allocations"))
					.header("Content-Type", "application/json")
	                .GET()
	                .build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			return switch (response.statusCode()) {
            case 200 -> objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, Allocation.class));
            case 204 -> throw new RuntimeException("No Content: No allocations found");
            case 500 -> throw new RuntimeException("Internal server error while fetching allocations");
            default -> throw new RuntimeException("Failed to fetch allocations with status code: " + response.statusCode());
			};
        
		} catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while fetching allocations", e);
        }
	}

	@Override
	public Allocation getAllocationDetails(Long allocationId) {
		 try {
	            HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(BASE_URL + "/plant/allocations/" + allocationId + "/details"))
	                .header("Content-Type", "application/json")
	                .GET()
	                .build();

	            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	            return switch (response.statusCode()) {
	                case 200 -> objectMapper.readValue(response.body(), Allocation.class);
	                case 404 -> throw new RuntimeException("Not Found: Allocation not found");
	                case 500 -> throw new RuntimeException("Internal server error while fetching allocation details");
	                default -> throw new RuntimeException("Failed to fetch allocation details with status code: " + response.statusCode());
	            };
	        } catch (IOException | InterruptedException e) {
	            throw new RuntimeException("Error while fetching allocation details", e);
	        }
	    }
	

	@Override
	public void makeAllocation(Long allocationId, Integer dumpsterNumber, Integer containerNumber, double totalWeight, String token) {
		try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/plant/allocations/" + allocationId + "/dumpsterNumber?amount=" + dumpsterNumber + "&containerNumber?amount=" + containerNumber + "&totalWeight?amount=" + totalWeight ))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(token))
                .build();

            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            switch (response.statusCode()) {
                case 204 -> {} // Bid placed successfully
                case 401 -> throw new RuntimeException("Unauthorized: User not authenticated");
                case 404 -> throw new RuntimeException("Not Found: Article not found");
                case 409 -> throw new RuntimeException("Conflict: Bid amount must be greater than the current price");
                case 500 -> throw new RuntimeException("Internal server error while placing a bid");
                default -> throw new RuntimeException("Failed to make a bid with status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while making a bid", e);
        }
	}

	@Override
	public int getCurrentCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Allocation getById() {
		// TODO Auto-generated method stub
		return null;
	}

}
